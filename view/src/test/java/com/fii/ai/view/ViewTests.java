package com.fii.ai.view;

import com.fii.ai.view.DTO.Word;
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
        view.getRelations(word, localPizza);
    }

    @Test
    public void test2(){
        Word word1 = new Word("Americana","cea mai buna pizza",10);
        Word word3 = new Word("America","USA",150);
        View exemplu = new View();
        try {
            exemplu.add(word1);
            exemplu.add(word3);
            for(Word i:exemplu.getWords())
                System.out.println(i.getNormalizeForm() +' ' + i.getTextForm() + ' '+ i.getOffset());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

