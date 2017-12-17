package com.fii.ai.view;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

public class ParserTests {
    @Test
    public void parserTest() throws OWLOntologyCreationException {

        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = manager.loadOntologyFromOntologyDocument(file);

        System.out.println("Loaded ontology: " + localPizza);

        Set<OWLAxiom> axioms = localPizza.getAxioms();
        System.out.println(axioms);
        //System.out.println(axioms.getClass());
        //for(OWLAxiom a : axioms)
        //    System.out.println(a);

        Set<OWLClass> concepts = new LinkedHashSet<OWLClass>();
        for(OWLClass cls : localPizza.getClassesInSignature()) {
            concepts.add(cls);
        }
        String x = ("Veneziana" + ">").toLowerCase();
        boolean check = false;
        for(OWLClass concept : concepts) {
            if((concept.toString().toLowerCase()).endsWith(x)) {
                check = true;
                System.out.println((concept.toString().toLowerCase()));
                break;
            }
        }
        System.out.println(check);

        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );

    }

    public boolean checkConcept(String x, OWLOntology ontology) {
        Set<OWLClass> concepts = new LinkedHashSet<OWLClass>();
        for(OWLClass cls : ontology.getClassesInSignature()) {
            concepts.add(cls);
        }
        for(OWLClass concept : concepts) {
            if((concept.toString().toLowerCase()).endsWith(x)) {
                return true;
            }
        }
        return false;
    }
}


