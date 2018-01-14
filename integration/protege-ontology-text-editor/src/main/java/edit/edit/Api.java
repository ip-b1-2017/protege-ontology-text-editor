package edit.edit;

import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import edit.util.Resolver;
import edit.util.ResolverException;

import java.util.HashSet;
import java.util.Set;

public class Api {
    private OWLWorkspace workspace;
    private Resolver resolver;

    public Api(OWLWorkspace workspace){
        this.workspace = workspace;
        this.resolver = new Resolver(workspace);
    }

    public void addRelation(String word1, String word2, String relation) throws ResolverException, ApiValueException {
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
        else {
            throw new ApiValueException("Can only add relations between classes or between individuals.");
        }
    }

    public void removeConcept(String word) throws ResolverException, ApiValueException {
        OWLEntity entity = resolver.resolveEntity(word);

        if(!(entity instanceof OWLClass)){
            throw new ApiValueException(word + " is not a concept.");
        }

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = workspace.getOWLModelManager().getActiveOntology();
        Set<OWLOntology> onts = new HashSet<>();
        onts.add(ontology);

        OWLEntityRemover remover = new OWLEntityRemover(onts);

        entity.accept(remover);

        manager.applyChanges(remover.getChanges());
        remover.reset();
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

    public void addInstanceOf(String word1, String word2) throws ResolverException, ApiValueException {
        OWLEntity entity1 = resolver.resolveEntity(word1);
        OWLEntity entity2 = resolver.resolveEntity(word2);

        if (entity1 instanceof OWLIndividual && entity2 instanceof OWLClass){
            linkOwlIndividualToClass((OWLClass)entity2, (OWLIndividual)entity1);
        }
        else{
            throw new ApiValueException("Can only set individual instance of class.");
        }
    }


    private void linkOwlIndividualToClass(OWLClass owlClass, OWLIndividual owlIndividual){
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();

        workspace.getOWLModelManager().applyChange(
                new AddAxiom(this.workspace.getOWLModelManager().getActiveOntology(),
                        factory.getOWLClassAssertionAxiom(owlClass, owlIndividual)));
    }


}
