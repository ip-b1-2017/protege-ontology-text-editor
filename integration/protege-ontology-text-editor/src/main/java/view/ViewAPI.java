package view;


import view.DTO.Relation;
import view.DTO.Word;
import org.semanticweb.owlapi.model.OWLOntology;
import java.util.ArrayList;
import java.util.List;

public class ViewAPI {
    public static List<Relation> getRelations(Word word, OWLOntology owlOntology, ArrayList<Word> words) {

            View view = new View();
            return  view.getAllRelations(word, owlOntology, words);

    }
}
