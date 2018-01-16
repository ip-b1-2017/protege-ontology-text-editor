package utilities;

import temp.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * Created by giosa on 18-Dec-17.
 */
public class Filter {

    public Filter(){

    }

    public boolean canAddRelation(){
        return LocalDatabase.addRelationClicked && LocalDatabase.currOffset!=-1;
    }

    public boolean canRemoveRelation(){
        return LocalDatabase.removeRelationClicked && LocalDatabase.currOffset!=-1;
    }

    public void addRelation(String relationName){
        LocalDatabase.relations.add(new Relation(LocalDatabase.currOffset,LocalDatabase.secondOffset, relationName));
        LocalDatabase.addRelationClicked = false;
    }

    public void setSecondOffset(ActionEvent e){
        LocalDatabase.secondOffset = LocalDatabase.wordButtons.indexOf(e.getSource());
    }

    public void setCurrOffset(ActionEvent e){
        LocalDatabase.currOffset = LocalDatabase.wordButtons.indexOf(e.getSource());
    }

    public JButton getCurrButton(){
        return LocalDatabase.wordButtons.get(LocalDatabase.currOffset);
    }

    public void clearWords(){
        for (int j = 0; j < LocalDatabase.wordButtons.size(); j++) {
            WordButton b = LocalDatabase.wordButtons.get(j);
            b.setOpaque(false);
        }
    }

    public void refreshPane(JPanel panel){
        panel.validate();
        panel.repaint();
    }

    public Relation hasRelation(Integer offset1, Integer offset2){
        for ( Relation i : LocalDatabase.relations  ) {
            if(Objects.equals(i.offset1, offset1) && Objects.equals(i.offset2, offset2)){
                return  i;
            }
        }
        return null;
    }

    public void addConcept(){
        LocalDatabase.conceptOffset.add(LocalDatabase.currOffset);
    }

    public void removeConcept(){
        LocalDatabase.conceptOffset.remove(LocalDatabase.currOffset);
    }

    public void removeRelation() {
        LocalDatabase.relations.remove(this.hasRelation(LocalDatabase.currOffset,LocalDatabase.secondOffset));
        LocalDatabase.wordButtons.get(LocalDatabase.secondOffset).setOpaque(false);
    }
}
