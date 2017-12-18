package com.fii.ai.view;

import com.fii.ai.view.DTO.OntologyRelation;
import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.util.List;

public class ViewAPI {
    public static List<Relation> getRelations(Word word){
        //Load the ontology
        File file = new File("tmp/pizza.owl");
        //TODO LOAD Ontology from Protege
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology owlOntology = null;
        try {
            owlOntology = manager.loadOntologyFromOntologyDocument(file);
            System.out.println("Loaded ontology: " + owlOntology);
        } catch (OWLOntologyCreationException e) {
            throw new InternalException("Failed to load the ontology");
        }
        View view = new View();
        // TODO: Comply wih GUI format
        return view.getRelations(word,owlOntology);
    }
}
