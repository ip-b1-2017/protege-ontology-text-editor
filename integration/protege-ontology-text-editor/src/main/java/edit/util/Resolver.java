package edit.util;

import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import java.util.Set;

public class Resolver {
    private OWLWorkspace workspace;

    public Resolver(OWLWorkspace workspace) {
        this.workspace = workspace;
    }

    private Set<OWLOntology> getOntologies(){
        return workspace.getOWLModelManager().getOntologies();
    }

    private boolean isEntityMatch(OWLOntology ontology, OWLEntity entity, String name){
        name = name.toLowerCase();
        if (getNameFromIRI(entity.getIRI()).toLowerCase().equals(name)){
            return true;
        }

        OWLAnnotationProperty label = workspace.getOWLModelManager().
                getOWLDataFactory().getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());

        for (OWLAnnotation annotation : entity.getAnnotations(ontology)){
            if (annotation.getValue() instanceof OWLLiteral &&
                    ((OWLLiteral) annotation.getValue()).getLiteral().equals(name)){
                return true;
            }
        }

        return false;
    }

    private boolean isPropertyMatch(OWLOntology ontology, OWLProperty property, String name){
        if (isEntityMatch(ontology, property, name)){
            return true;
        }

        return false;
    }

    private String getNameFromIRI(IRI iri){
        return iri.getFragment();
    }

    public OWLEntity resolveEntity(String name) throws ResolverException {
        for (OWLOntology ontology: getOntologies()) {
            for (OWLClass owlClass : ontology.getClassesInSignature()) {
                if (isEntityMatch(ontology, owlClass, name)) {
                    return owlClass;
                }
            }

            for (OWLNamedIndividual individual : ontology.getIndividualsInSignature()) {
                if (isEntityMatch(ontology, individual, name)){
                    return individual;
                }
            }
        }

        throw new ResolverException(name, "OWL Entity");
    }

    public OWLProperty resolveProperty(String relation) throws ResolverException {
        for (OWLOntology ontology: getOntologies()) {
            for (OWLProperty owlProperty : ontology.getObjectPropertiesInSignature()){
                if (isPropertyMatch(ontology, owlProperty, relation)){
                    return owlProperty;
                }
            }
        }

        throw new ResolverException(relation, "OWL Property");
    }
}
