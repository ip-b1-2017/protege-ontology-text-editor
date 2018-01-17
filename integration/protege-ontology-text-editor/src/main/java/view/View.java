package view;

import view.DTO.OntologyRelation;
import view.DTO.Relation;
import view.DTO.Word;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Retrieve all relations which contain the given word
 */
public class View {

    public List<Relation> getRelations(Word word, OWLOntology ontology) {
        List<OntologyRelation> ontologyRelations;
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLReasoner reasoner = new StructuralReasoner(ontology, new SimpleConfiguration(),
                BufferingMode.NON_BUFFERING);

        String localWord = word.getNormalizeForm().toLowerCase() + ">";

        for (OWLClass class1 : ontology.getClassesInSignature()) {
            //System.out.println(class1.getClass());
            //String classString = class1.toString().toLowerCase().endsWith(localWord);
            if(class1.toString().toLowerCase().endsWith(localWord))
                for (OWLObjectPropertyExpression prop : ontology.getObjectPropertiesInSignature()) {
                    OWLClassExpression restriction = df.getOWLObjectSomeValuesFrom(prop, df.getOWLThing());

                    OWLClassExpression intersection = df.getOWLObjectIntersectionOf(class1, df.getOWLObjectComplementOf(restriction));
                    if (!reasoner.isSatisfiable(intersection))

                        System.out.println("Instaances of" + class1 + " have " + prop);
                       System.out.println(restriction+ " " + df.getOWLObjectComplementOf(restriction));
                        System.out.println(restriction +  " "+ df.getOWLObjectComplementOf(restriction).getOperand());
                }

        }

        return null;
    }
    private  ArrayList<Word> words = new ArrayList<Word>();

    public List<Relation> getRelations(Word word){
       return null;

    }

    public  ArrayList<Word> getWords() {
        return words;
    }

    public  void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void add(Word word){
        words.add(word);
    }

}

