package edu.stanford.bmir.protege.examples.view;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;

import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import temp.Mocker;
import temp.Relation;
import utilities.LocalDatabase;
import utilities.OWLApi;
import utilities.WordButton;
import utilities.WrapLayout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PluginViewComponent extends AbstractOWLViewComponent {

    private static final Logger log = LoggerFactory.getLogger(PluginViewComponent.class);


    @Override
    protected void initialiseOWLView() throws Exception {
        OWLApi.initializeEditApi(this.getOWLWorkspace());
        setLayout(new BorderLayout());

        JPanel flowPane = new JPanel();
        flowPane.setBackground(Color.white);
        flowPane.setLayout(new WrapLayout(FlowLayout.LEADING));
        flowPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JPanel gridPane = new JPanel();
        gridPane.setLayout(new GridLayout(2,4));


        JTextArea textPane = new JTextArea();
        textPane.setLineWrap(true);
        textPane.setEditable(false);

        initialiseMenuButtons(gridPane,textPane);

        JScrollPane scrollPane = new JScrollPane(flowPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLayout(new ScrollPaneLayout());

        JScrollPane textScroll = new JScrollPane(textPane);
        textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScroll.setLayout(new ScrollPaneLayout());

        Mocker mocker = new Mocker();

        for (int i=0; i<mocker.words.size();i++){
            LocalDatabase.wordButtons.add(new WordButton(mocker.words.get(i).getWord()));


            LocalDatabase.wordButtons.get(i).addActionListener(e -> {
                if (LocalDatabase.addRelationClicked && LocalDatabase.currOffset!=-1){
                    LocalDatabase.secondOffset = LocalDatabase.wordButtons.indexOf(e.getSource());
                    String input = JOptionPane.showInputDialog("What relation between *" + LocalDatabase.wordButtons.get(LocalDatabase.currOffset).getText()  + "*  and  *" +  ((JButton)e.getSource()).getText() + "* \nwould you like to add?");
                    LocalDatabase.relations.add(new Relation(LocalDatabase.currOffset,LocalDatabase.secondOffset, input));
                    LocalDatabase.addRelationClicked = false;
                }
                else
                if (LocalDatabase.removeRelationClicked && LocalDatabase.currOffset!=-1) {
                    LocalDatabase.secondOffset = LocalDatabase.wordButtons.indexOf(e.getSource());
                    int input = JOptionPane.showConfirmDialog(
                            gridPane,
                            "You sure you want to delete relation between *" + ((JButton)e.getSource()).getText()  + "*  and  *" + LocalDatabase.wordButtons.get(LocalDatabase.currOffset).getText() + "* ?",
                            "Confirm action",
                            JOptionPane.YES_NO_OPTION);
                    if (input == 0 ) {
                        LocalDatabase.relations.remove(mocker.hasRelation(LocalDatabase.currOffset,LocalDatabase.secondOffset));
                        LocalDatabase.wordButtons.get(LocalDatabase.secondOffset).setOpaque(false);
                    }
                    LocalDatabase.removeRelationClicked = false;
                }
                else {
                    LocalDatabase.currOffset = LocalDatabase.wordButtons.indexOf(e.getSource());
                    JButton currButt = LocalDatabase.wordButtons.get(LocalDatabase.currOffset);
                    textPane.setText(currButt.getText());
                    for (int j = 0; j < mocker.words.size(); j++) {
                        WordButton b = LocalDatabase.wordButtons.get(j);
                        b.setOpaque(false);
                    }
                    currButt.setBackground(Color.GREEN);
                    currButt.setOpaque(true);
                    flowPane.validate();
                    flowPane.repaint();
                    for (int j = 0; j < LocalDatabase.wordButtons.size(); j++) {
                        Relation rel = mocker.hasRelation(LocalDatabase.currOffset, j);

                        System.out.println(j);
                        if (rel != null) {
                            LocalDatabase.wordButtons.get(j).setBackground(Color.GREEN);
                            LocalDatabase.wordButtons.get(j).setOpaque(true);
                            textPane.append(currButt.getText() + " " + rel.relation + " " + LocalDatabase.wordButtons.get(rel.offset2).getText() + ".\n");
                        }
                    }
                }
                flowPane.validate();
                flowPane.repaint();
            });
            flowPane.add(LocalDatabase.wordButtons.get(i));
        }


        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, textScroll);
        splitPaneVertical.setDividerLocation(650);
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneVertical, gridPane);
        splitPaneHorizontal.setPreferredSize(new Dimension(800, 600));
        splitPaneHorizontal.setOneTouchExpandable(true);
        splitPaneHorizontal.setDividerLocation(500);
        add(splitPaneHorizontal);
        log.info("Example View Component initialized");
    }

    private void initialiseMenuButtons(JPanel gridPane, JTextArea textArea) {
        JButton addRelation = new JButton("Add relation");
        addRelation.setBackground(Color.GREEN);
        addRelation.addActionListener( e -> LocalDatabase.addRelationClicked = true);


        JButton removeRelation = new JButton("Remove relation");
        removeRelation.setBackground(Color.GREEN);
        removeRelation.addActionListener(e -> LocalDatabase.removeRelationClicked = true);



        JButton conceptMarking = new JButton("Unmark/Mark as concept");
        conceptMarking.setBackground(Color.RED);
        conceptMarking.addActionListener(e -> {
            WordButton  currBut = LocalDatabase.wordButtons.get(LocalDatabase.currOffset);
            if (!LocalDatabase.conceptOffset.contains(LocalDatabase.currOffset)) {
                textArea.setText(currBut.getText() + " was marked as concept.\n");
                currBut.setBackground(Color.RED);
                LocalDatabase.conceptOffset.add(LocalDatabase.currOffset);

            }
            else
            if (LocalDatabase.conceptOffset.contains(LocalDatabase.currOffset)){
                textArea.setText(currBut.getText() + " is a concept no more.\n");
                currBut.setOpaque(false);
                LocalDatabase.conceptOffset.remove(LocalDatabase.currOffset);
            }
            currBut.getRootPane().validate();
            currBut.getRootPane().repaint();
        });



        JButton seeConcepts = new JButton("See all concepts");
        seeConcepts.setBackground(Color.RED);
        seeConcepts.addActionListener(e -> {
            textArea.setText("");
            WordButton b = LocalDatabase.wordButtons.get(0);
            for (int i=0;i<LocalDatabase.wordButtons.size();i++){
                b = LocalDatabase.wordButtons.get(i);
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                if (LocalDatabase.conceptOffset.contains(i)){
                    textArea.append(b.getText() + " is a Concept" + '\n');
                    b.setBackground(Color.RED);
                    b.setOpaque(true);
                }
            }
            b.getRootPane().validate();
            b.getRootPane().repaint();
        });


        JButton load = new JButton("Load File");
        load.setBackground(Color.CYAN);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF and TXT texts", "pdf", "txt");
        fileChooser.addChoosableFileFilter(filter);
        load.addActionListener(e->{
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });


        JButton save = new JButton("Save File");
        save.setBackground(Color.CYAN);

        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filterSave = new FileNameExtensionFilter("Text(*.txt)",  "txt");
        chooser.addChoosableFileFilter(filterSave);
        save.addActionListener(e->{
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try(FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {
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
