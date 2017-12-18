package com.fii.ai.edit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class tests {
    private OWLReasoner reasoner;
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    private OWLDataFactory fac;

    @Before
    public void initialize(){
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        manager = OWLManager.createOWLOntologyManager();
        fac = manager.getOWLDataFactory();
        try {
            ontology = load(manager);
            reasoner = reasonerFactory.createReasoner(ontology);
            reasoner.precomputeInferences();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
        manager = null;
        ontology = null;
        reasoner = null;
        fac = null;
    }

    @Test
    public void parserTest(){
        for (OWLClass cls : ontology.getClassesInSignature()) {
            System.out.println(cls);
        }

    }

    @Test
    public void removeConcept() {
        for (OWLClass cls: ontology.getClassesInSignature()
                ) {
            System.out.println(cls);
        }
        System.out.println("--------------------------------------");
        Set<OWLOntology> onts = new HashSet<>();
        onts.add(ontology);
        OWLEntityRemover remover = new OWLEntityRemover(manager, onts);
        for(OWLClass ind: ontology.getClassesInSignature()){
            if(ind.toString().contains("MeatTopping"))
                ind.accept(remover);
        }
        manager.applyChanges(remover.getChanges());
        remover.reset();

        for (OWLClass cls: ontology.getClassesInSignature()
             ) {
            System.out.println(cls);
        }
    }

    @Test
    public void addConcept() {
        Set<OWLOntology> onts = new HashSet<>();
        onts.add(ontology);
        OWLEntityRemover remover = new OWLEntityRemover(manager, onts);
        for (OWLNamedIndividual ind : ontology.getIndividualsInSignature()) {
            if(ind.toString().contains("America"))
                ind.accept(remover);
        }
        manager.applyChanges(remover.getChanges());
        remover.reset();
        OWLClass newClass = fac.getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI()+"/pizza.owl#America"));
        OWLClass thing = fac.getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI()+"/pizza.owl#DomainConcept"));
        OWLAxiom axiom = fac.getOWLSubClassOfAxiom(newClass, thing);
        AddAxiom add = new AddAxiom(ontology, axiom);
        manager.applyChange(add);
        Node<OWLClass> topNode = reasoner.getTopClassNode();
        print(topNode, reasoner, 0);
    }

    private void print(Node<OWLClass> parent, OWLReasoner reasoner, int depth) {
        if (parent.isBottomNode()) {
            return;
        }
        printIndent(depth);
        printNode(parent);
        for (Node<OWLClass> child : reasoner.getSubClasses(parent.getRepresentativeElement(), true)) {
            assert child != null;
            print(child, reasoner, depth + 1);
        }
    }


    private void printIndent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
    }

    private static void printNode(Node<OWLClass> node) {
        DefaultPrefixManager pm = new DefaultPrefixManager();
        for (OWLClass cls : node.getEntities()) {
            String shortForm = pm.getShortForm(cls);
            System.out.println(shortForm);
        }
    }


    private OWLOntology load(OWLOntologyManager manager) throws OWLOntologyCreationException {
        File file = new File("tmp/pizza.owl");
        return manager.loadOntologyFromOntologyDocument(file);
    }


}
