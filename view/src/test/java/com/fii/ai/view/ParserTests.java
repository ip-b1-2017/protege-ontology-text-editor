package com.fii.ai.view;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyDomainAxiomImpl;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;


public class ParserTests {

   @Test
    public void checkConceptTest() throws OWLOntologyCreationException {
        String concept = "American";
        Parser p = new Parser();
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        IRI iri = IRI.create("tmp/pizza.owl");
        File file = new File("tmp/pizza.owl");
        OWLOntology owlOntology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println(p.checkConcept(concept,owlOntology));
    }

    @Test
    public void parserTest() throws OWLOntologyCreationException {

        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded ontology: " + localPizza);
/*
        System.out.println(localPizza.getFormat());
        System.out.println(localPizza.components());
        Stream components = localPizza.components();
        Object[] list = components.toArray();


        for(Object obj : list){
            System.out.println("class " + obj.getClass());
            System.out.println("toString " + obj.toString());
        }
        OWLAxiom axiom;
        Stream<OWLAxiom> axioms =  localPizza.axioms();
        OWLObjectPropertyDomainAxiomImpl pizza;

        for(Object obj : axioms.toArray()){
            OWLAxiom axiom1 = (OWLAxiom) obj;
            System.out.println(axiom1.getClass());
            System.out.println(Arrays.toString(axiom1.components().toArray()));
            //   System.out.println("class " + obj.getClass());
            System.out.println("toString " + obj.toString());
        }

*/
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );

    }

}


