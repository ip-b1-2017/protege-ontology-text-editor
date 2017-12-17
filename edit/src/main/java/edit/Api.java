package edit;

import org.apache.jena.vocabulary.OWL;
import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import util.Resolver;
import util.ResolverException;

import java.util.Set;

public class Api {
    private OWLWorkspace workspace;
    private Resolver resolver;

    public Api(OWLWorkspace workspace){
        this.workspace = workspace;
        this.resolver = new Resolver(workspace);
    }

    public void addRelation(String word1, String word2, String relation) throws ResolverException {
        OWLEntity entity1 = resolver.resolveEntity(word1);
        OWLEntity entity2 = resolver.resolveEntity(word2);

        OWLProperty property = resolver.resolveProperty(relation);

        if (entity1 instanceof OWLClass &&
                entity2 instanceof OWLClass &&
                property instanceof OWLObjectProperty) {
            linkOwlClasses(
                    (OWLClass)entity1,
                    (OWLClass)entity2,
                    (OWLObjectProperty)property);
        }
        else if (entity1 instanceof OWLNamedIndividual &&
                entity2 instanceof OWLNamedIndividual &&
                property instanceof OWLObjectProperty) {
            linkOwlIndividuals(
                    (OWLIndividual)entity1,
                    (OWLIndividual)entity2,
                    (OWLObjectProperty)property
            );
        }
    }

    private void linkOwlClasses(OWLClass class1, OWLClass class2, OWLObjectProperty property) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();

        workspace.getOWLModelManager().applyChange(
                new AddAxiom(this.workspace.getOWLModelManager().getActiveOntology(),
                        factory.getOWLSubClassOfAxiom(
                                class1,
                                factory.getOWLObjectSomeValuesFrom(property, class2))));
    }

    private void linkOwlIndividuals(OWLIndividual ind1, OWLIndividual ind2, OWLObjectProperty property){
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();

        workspace.getOWLModelManager().applyChange(
                new AddAxiom(this.workspace.getOWLModelManager().getActiveOntology(),
                        factory.getOWLObjectPropertyAssertionAxiom(
                                property,
                                ind1,
                                ind2)));
    }

    private void linkOwlIndividualToClass(OWLClass owlClass, OWLIndividual owlIndividual){
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();

        workspace.getOWLModelManager().applyChange(
                new AddAxiom(this.workspace.getOWLModelManager().getActiveOntology(),
                        factory.getOWLClassAssertionAxiom(owlClass, owlIndividual)));
    }


}
