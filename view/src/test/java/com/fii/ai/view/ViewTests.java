package com.fii.ai.view;

import com.fii.ai.view.DTO.Word;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void test1(){
        View view = new View();
        Word word = new Word("Veneziana","sasa",1);
        File file = new File("tmp/pizza.owl");
        // Now load the local copy
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology localPizza = null;
        try {
            localPizza = manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
      //  view.getRelations(word, localPizza);
    }

    @Test
    public void test2(){
        Word word1 = new Word("Americana","cea mai buna pizza",10);
        Word word3 = new Word("America","USA",150);
        View exemplu = new View();
       /*
        try {
            exemplu.add(word1);
            exemplu.add(word3);
            for(Word i:exemplu.getWords())
                System.out.println(i.getNormalizeForm() +' ' + i.getTextForm() + ' '+ i.getOffset());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        */
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
        localPizza.getAxioms( AxiomType.SUBCLASS_OF);
        List<OWLAxiom> axioms = new ArrayList<>(localPizza.getAxioms());
        System.out.println( axioms.get(1).getAxiomType());
        System.out.println(axioms.get(347).toString());

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

