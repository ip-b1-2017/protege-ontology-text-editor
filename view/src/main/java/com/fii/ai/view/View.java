package com.fii.ai.view;

import com.fii.ai.view.DTO.OntologyRelation;
import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;

import java.util.List;

/**
 * TODO: Retrieve all relations which contain the given word
 */
public class View {

    public List<Relation> getRelations(Word word, OWLOntology ontology){
        List<OntologyRelation> ontologyRelations;
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasoner(ontology, new SimpleConfiguration(),
                BufferingMode.NON_BUFFERING);

        for(OWLClass class1 : ontology.getClassesInSignature()) {
            for (OWLObjectPropertyExpression prop : ontology.getObjectPropertiesInSignature()) {
                OWLClassExpression restriction = df.getOWLObjectSomeValuesFrom(prop, df.getOWLThing());

                OWLClassExpression intersection = df.getOWLObjectIntersectionOf(class1, df.getOWLObjectComplementOf(restriction));
                if (!reasoner.isSatisfiable(intersection))
                   System.out.println("Instaances of" + class1 + " have " + prop);
                   // System.out.println(restriction+ " " + df.getOWLObjectComplementOf(restriction));
                    //System.out.println(restriction +  " "+ df.getOWLObjectComplementOf(restriction).getOperand());
            }

        }

            return null;
    }

}

