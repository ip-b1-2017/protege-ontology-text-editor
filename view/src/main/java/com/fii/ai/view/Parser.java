package com.fii.ai.view;

import com.fii.ai.view.DTO.OntologyRelation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO: Add a parser for the ontology, save all possible relations
 */
public class Parser {
    public List<OntologyRelation> doParse(String path) throws OWLOntologyCreationException {

        return null;
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
}]
