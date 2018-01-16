package utilities;

import temp.Relation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giosa on 18-Dec-17.
 */
public class LocalDatabase {

    public static List<WordButton> wordButtons  = new ArrayList<>();
    public static List<Integer> conceptOffset;
    public static List<Relation> relations;
    public static Integer currOffset = -1;
    public static Integer secondOffset;
    public static boolean addRelationClicked = false;
    public static boolean removeRelationClicked = false;


}
