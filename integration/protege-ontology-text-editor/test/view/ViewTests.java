package view;

import org.semanticweb.owlapi.model.AxiomType;
import view.DTO.Word;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class ViewTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void test1(){
        View view = new View();
        File file = new File("tmp/pizza.owl");        Word word = new Word("Veneziana","sasa",1);

        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = null;
        try {
            localPizza = manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        view.getRelations(word, localPizza);
    }


    @Test
    public void test3(){
        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = null;
        try {
            localPizza = manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        System.out.println(localPizza.getAxiomCount());
        for( int i =0 ; i<localPizza.getAxiomCount();++i){


        }
        System.out.println( localPizza.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE));
    }
    @Test
    public void test4(){
        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = null;
        try {
            localPizza = manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        View view = new View();
        Word word = new Word();

    }
}

