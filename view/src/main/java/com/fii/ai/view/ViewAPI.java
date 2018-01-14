package com.fii.ai.view;

import com.fii.ai.view.DTO.OntologyRelation;
import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewAPI {
    public static List<Relation> getRelations(Word word, OWLOntology owlOntology, ArrayList<Word> words) throws FailedToLoadOntologyException {
        //Load the ontology
        File file = new File("tmp/pizza.owl");
        //TODO LOAD Ontology from Protege
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        if(owlOntology == null){
            try {
                owlOntology = manager.loadOntologyFromOntologyDocument(file);
                System.out.println("Loaded ontology: " + owlOntology);
            } catch (OWLOntologyCreationException e) {
                throw new FailedToLoadOntologyException("Failed to load the ontology");
            }
        }
        View view = new View();
        view.setWords(words);
        // TODO: Comply wih GUI format
        return view.getRelations(word,owlOntology);
    }
}
