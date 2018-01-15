package utilities;

import edit.edit.ApiValueException;
import validation.src.WordLemaTuple;
import view.DTO.Word;
import view.Parser;
import view.ViewAPI;
import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.model.OWLOntology;
import temp.LemaWord;
import temp.Relation;
import edit.edit.Api;
import edit.util.ResolverException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rusub on 12/18/2017.
 */
public class OWLApi {

    private static Api editApi;
    private static OWLOntology ontology;
    private static List<WordLemaTuple> text = null;
    private static List<Word> viewText = new ArrayList<>();

    public static void initializeEditApi(OWLWorkspace workspace){
        System.out.println("[B1 PROTEGE] Works " + workspace.getName());
        editApi = new Api(workspace);
    }

    public static void setOntology(OWLOntology currentOnto){
        ontology = currentOnto;
    }

    public static void setText(List<WordLemaTuple> t){
        if(text != null)
            return;
        text = t;
        for (int it = 0; it < text.size(); ++it){
            viewText.add(new Word(
                    text.get(it).getLema(),
                    text.get(it).getWord(),
                    it));
        }
    }
    public static List<Relation> getRelationsOfWord(Integer wordOffset){
        assert text != null : "Text must be not null";
        List<Relation> wordRelations = new ArrayList<>();
        List<view.DTO.Relation> ret =
                ViewAPI.getRelations(
                        viewText.get(wordOffset),
                        ontology,
                        (ArrayList<Word>) viewText);
        for (view.DTO.Relation it : ret){
            wordRelations.add(new Relation(it.getOffset1(), it.getOffset2(), it.getRelationName()));
        }
        return wordRelations;
    }

    public static boolean isConcept(Integer wordOffset){
        assert text != null : "Text must be not null";
        String lemmaWord = text.get(wordOffset).getLema();
        Parser p = new Parser();
        return p.checkConcept(lemmaWord, ontology);
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
