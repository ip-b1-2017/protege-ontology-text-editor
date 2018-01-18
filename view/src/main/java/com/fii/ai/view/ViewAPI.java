package com.fii.ai.view;

import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewAPI {
   public static List<Relation> getRelations(Word word, OWLOntology owlOntology, ArrayList<Word> words) throws FailedToLoadOntologyException, OWLOntologyCreationException {

        View view = new View();
        //TODO LOAD Ontology from Protege
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        if(owlOntology == null){
            File file = new File("tmp/pizza.owl");
            try {
                owlOntology = manager.loadOntologyFromOntologyDocument(file);
                System.out.println("Loaded ontology: " + owlOntology);
            } catch (OWLOntologyCreationException e) {
                throw new FailedToLoadOntologyException("Failed to load the ontology");
            }
        }
        // TODO: Comply wih GUI format
        else{
                manager = owlOntology.getOWLOntologyManager();
        }
        IRI iri = IRI.create("/tmp");
        manager.loadOntology(iri);
      //  view.setWords(words);
        return view.getRelations(word,owlOntology,words);
    }
}
