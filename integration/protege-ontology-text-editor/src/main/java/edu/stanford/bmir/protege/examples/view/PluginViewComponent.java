package edu.stanford.bmir.protege.examples.view;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import edit.edit.ApiValueException;
import edit.util.ResolverException;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import temp.Relation;
import utilities.*;
import validation.src.InformationProcessor;
import validation.src.WordLemaTuple;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PluginViewComponent extends AbstractOWLViewComponent {

    private static final Logger log = LoggerFactory.getLogger(PluginViewComponent.class);

    Filter filter = new Filter();
    JPanel flowPane = new JPanel();
    private List<WordLemaTuple> words = null;
    boolean loaded = false;
    JPanel gridPane;
    JTextArea textPane;
    JScrollPane scrollPane;
    JScrollPane textScroll;

    @Override
    protected void initialiseOWLView() throws Exception {
        OWLApi.initializeEditApi(this.getOWLWorkspace());
        setLayout(new BorderLayout());

        flowPane.setBackground(Color.white);
        flowPane.setLayout(new WrapLayout(FlowLayout.LEADING));
        flowPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        gridPane = new JPanel();
        gridPane.setLayout(new GridLayout(2, 4));


        textPane = new JTextArea();
        textPane.setLineWrap(true);
        textPane.setEditable(false);

        initialiseMenuButtons(gridPane, textPane);

        scrollPane = new JScrollPane(flowPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLayout(new ScrollPaneLayout());

        textScroll = new JScrollPane(textPane);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScroll.setLayout(new ScrollPaneLayout());




        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, textScroll);
        splitPaneVertical.setDividerLocation(650);
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneVertical, gridPane);
        splitPaneHorizontal.setPreferredSize(new

                Dimension(800, 600));
        splitPaneHorizontal.setOneTouchExpandable(true);
        splitPaneHorizontal.setDividerLocation(500);

        add(splitPaneHorizontal);
        log.info("Example View Component initialized");
    }

    private void addWordsToPane(){
        for (int i = 0; i < words.size(); i++) {
            LocalDatabase.wordButtons.add(new WordButton(words.get(i).getWord()));
            LocalDatabase.wordButtons.get(i).addActionListener(e -> {
                try {
                    if (filter.canAddRelation()) {
                        LocalDatabase.addRelationClicked = false;
                        filter.setSecondOffset(e);
                        String input = JOptionPane.showInputDialog("What relation between *" + LocalDatabase.wordButtons.get(LocalDatabase.currOffset).getText() + "*  and  *" + ((JButton) e.getSource()).getText() + "* \nwould you like to add?");
                        OWLApi.addRelation(LocalDatabase.currOffset,LocalDatabase.secondOffset, input);
                    } else if (filter.canRemoveRelation()) {
                        filter.setSecondOffset(e);
                        int input = JOptionPane.showConfirmDialog(
                                gridPane,
                                "You sure you want to delete relation between *" + ((JButton) e.getSource()).getText() + "*  and  *" + LocalDatabase.wordButtons.get(LocalDatabase.currOffset).getText() + "* ?",
                                "Confirm action",
                                JOptionPane.YES_NO_OPTION);
                        if (input == 0) {
                            filter.removeRelation();
                        }
                        LocalDatabase.removeRelationClicked = false;
                    } else {
                        filter.setCurrOffset(e);
                        List<Relation> relations = OWLApi.getRelationsOfWord(LocalDatabase.currOffset);
                        System.out.println("RELATION >>>>>>>>>> " + relations);
                        JButton currButt = filter.getCurrButton();
                        textPane.setText(currButt.getText());
                        filter.clearWords();
                        currButt.setBackground(Color.GREEN);
                        currButt.setOpaque(true);
                        filter.refreshPane(flowPane);

                        for (Relation relation : relations){
                            if(relation.getOffset1() != LocalDatabase.currOffset){
                                LocalDatabase.wordButtons.get(relation.getOffset1()).setBackground(Color.GREEN);
                                LocalDatabase.wordButtons.get(relation.getOffset1()).setOpaque(true);
                            }
                            else {
                                LocalDatabase.wordButtons.get(relation.getOffset2()).setBackground(Color.GREEN);
                                LocalDatabase.wordButtons.get(relation.getOffset2()).setOpaque(true);
                            }
                            //textPane.append(currButt.getText() + " " + rel.relation + " " + LocalDatabase.wordButtons.get(rel.offset2).getText() + ".\n");
                        }

//                        for (int j = 0; j < LocalDatabase.wordButtons.size(); j++) {
//                            Relation rel = filter.hasRelation(LocalDatabase.currOffset, j);
//                            if (rel != null) {
//                                LocalDatabase.wordButtons.get(j).setBackground(Color.GREEN);
//                                LocalDatabase.wordButtons.get(j).setOpaque(true);
//
//                            }
//                        }
                    }
                    filter.refreshPane(flowPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(flowPane,
                            "" + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            flowPane.add(LocalDatabase.wordButtons.get(i));
        }

    }

    private void initialiseMenuButtons(JPanel gridPane, JTextArea textArea) {
        JButton addRelation = new JButton("Add relation");
        addRelation.setBackground(Color.GREEN);
        addRelation.addActionListener(e -> LocalDatabase.addRelationClicked = true);


        JButton removeRelation = new JButton("Remove relation");
        removeRelation.setBackground(Color.GREEN);
        removeRelation.addActionListener(e -> LocalDatabase.removeRelationClicked = true);


        JButton conceptMarking = new JButton("Unmark/Mark as concept");
        conceptMarking.setBackground(Color.RED);
        conceptMarking.addActionListener(e -> {
            WordButton currBut = LocalDatabase.wordButtons.get(LocalDatabase.currOffset);
            if (!OWLApi.isConcept(LocalDatabase.currOffset)) {
                textArea.setText(currBut.getText() + " was marked as concept.\n");
                currBut.setBackground(Color.RED);
                filter.addConcept();

            } else if (OWLApi.isConcept(LocalDatabase.currOffset)) {
                try {
                    OWLApi.removeConcept(LocalDatabase.currOffset);
                } catch (ApiValueException | ResolverException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(flowPane,
                            "" + e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                textArea.setText(currBut.getText() + " is a concept no more.\n");
                currBut.setOpaque(false);
                filter.removeConcept();
            }
            filter.refreshPane(flowPane);
        });


        JButton seeConcepts = new JButton("See all concepts");
        seeConcepts.setBackground(Color.RED);
        seeConcepts.addActionListener(e -> {
            textArea.setText("");
            WordButton b = LocalDatabase.wordButtons.get(0);
            for (int i = 0; i < LocalDatabase.wordButtons.size(); i++) {
                b = LocalDatabase.wordButtons.get(i);
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                if (OWLApi.isConcept(i)) {
                    textArea.append(b.getText() + " is a Concept" + '\n');
                    b.setBackground(Color.RED);
                    b.setOpaque(true);
                }
            }
            filter.refreshPane(flowPane);
        });


        JButton load = new JButton("Load File");
        load.setBackground(Color.CYAN);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF and TXT texts", "pdf", "txt");
        fileChooser.addChoosableFileFilter(filter);
        load.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                InformationProcessor informationProcessor = new InformationProcessor(selectedFile.getAbsolutePath());
                try {
                    words = informationProcessor.getWordLemaTuples();
                    System.out.println(words);

                    OWLApi.setText(words);

                    addWordsToPane();
                    this.filter.refreshPane(flowPane);

                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        });


        JButton save = new JButton("Save File");
        save.setBackground(Color.CYAN);

        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filterSave = new FileNameExtensionFilter("Text(*.txt)", "txt");
        chooser.addChoosableFileFilter(filterSave);
        save.addActionListener(e -> {
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt")) {
                    fw.write(sb.toString());
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        gridPane.add(addRelation);
        gridPane.add(conceptMarking);
        gridPane.add(load);
        gridPane.add(removeRelation);
        gridPane.add(seeConcepts);
        gridPane.add(save);
    }




    @Override
    protected void disposeOWLView() {

    }
}
