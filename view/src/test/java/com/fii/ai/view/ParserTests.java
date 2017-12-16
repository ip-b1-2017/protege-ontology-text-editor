package com.fii.ai.view;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class ParserTests {
    @Test
    public void parserTest() throws OWLOntologyCreationException {

        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded ontology: " + localPizza);
    }
}