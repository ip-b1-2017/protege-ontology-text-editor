package view;

import view.DTO.OntologyRelation;
import view.DTO.Relation;
import view.DTO.Word;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewAPI {
    public static List<Relation> getRelations(Word word, OWLOntology owlOntology, ArrayList<Word> words){
        //Load the ontology
        File file = new File("tmp/pizza.owl");
        //TODO LOAD Ontology from Protege
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        if(owlOntology == null){
            try {
                owlOntology = manager.loadOntologyFromOntologyDocument(file);
                System.out.println("Loaded ontology: " + owlOntology);
            } catch (OWLOntologyCreationException e) {
                throw new InternalException("Failed to load the ontology");
            }
        }
        View view = new View();
        view.setWords(words);
        // TODO: Comply wih GUI format
        return view.getRelations(word,owlOntology);
    }
}
