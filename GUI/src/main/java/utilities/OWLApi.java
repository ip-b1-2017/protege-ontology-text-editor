package utilities;

import edu.stanford.bmir.protege.examples.view.PluginViewComponent;
import org.protege.editor.owl.model.OWLWorkspace;
import temp.LemaWord;
import temp.Relation;
import edit.*;
import util.ResolverException;

import java.util.List;

/**
 * Created by rusub on 12/18/2017.
 */
public class OWLApi {

    private static Api editApi;
    private static List<LemaWord> text = null;

    public static void initializeEditApi(OWLWorkspace workspace){
        editApi = new Api(workspace);
    }
    public static void setText(List<LemaWord> t){
        text = t;
    }
    public static List<Relation> getRelationsOfWord(Integer wordOffset){
        assert text != null : "Text must be not null";
        List<Relation> wordRelations = null;
        /*
            TODO call View api for viewing relations of word positioned in text at index wordOffset
         */
        return wordRelations;
    }

    public static boolean isConcept(Integer wordOffset){
        assert text != null : "Text must be not null";
        String lemmaWord = text.get(wordOffset).getLema();
        boolean status = false;
        /*
            TODO call View api for checking if lemmaWord is a concept or not
         */
        return status;
    }

    public static void addRelation(Integer wordOffset1,
                                   Integer wordOffset2,
                                   String relation) throws ApiValueException, ResolverException {
        assert text != null : "Text must be not null";
        assert editApi != null : "Must initialize editApi";
        String lemmaWord1 = text.get(wordOffset1).getLema();
        String lemmaWord2 = text.get(wordOffset2).getLema();
        editApi.addRelation(lemmaWord1, lemmaWord2, relation);
    }

    /*
        will make a link word1 and word2 like this:
            word1 instanceOf word2
     */
    public static void addInstanceOf(Integer wordOffset1,
                                     Integer wordOffset2) throws ApiValueException, ResolverException {
        assert text != null : "Text must be not null";
        assert editApi != null : "Must initialize editApi";
        String lemmaWord1 = text.get(wordOffset1).getLema();
        String lemmaWord2 = text.get(wordOffset2).getLema();
        editApi.addInstanceOf(lemmaWord1, lemmaWord2);
    }

    public static void removeConcept(Integer wordOffset) throws ApiValueException, ResolverException {
        assert text != null : "Text must be not null";
        assert editApi != null : "Must initialize editApi";
        String lemmaWord = text.get(wordOffset).getLema();
        editApi.removeConcept(lemmaWord);
    }
}
