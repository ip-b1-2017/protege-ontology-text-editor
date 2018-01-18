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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Retrieve all relations which contain the given word
 */
public class View {

    private static final Pattern TAG_REGEX = Pattern.compile("#(.+?)>");
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
                        System.out.println("property" + prop);
                    System.out.println("property name" + prop.getNamedProperty());
                    System.out.println("inverse property" + prop.getInverseProperty());

                    System.out.println("Instaances of" + class1 + " have " + prop);
                    // System.out.println(restriction+ " " + df.getOWLObjectComplementOf(restriction));
                    //System.out.println(restriction +  " "+ df.getOWLObjectComplementOf(restriction).getOperand());
                }

        }

        return null;
    }


    public List<Relation> getAllRelations(Word word, OWLOntology ontology, List<Word> words) {


        List<Relation> relations = new ArrayList<>();
        String str = word.getNormalizeForm().toLowerCase();
        List<OWLAxiom> axioms = new ArrayList<>(ontology.getAxioms());
        for( int axiomIndex = 0; axiomIndex<ontology.getAxiomCount(); ++axiomIndex) {


            final List<String> tagValues = new ArrayList<String>();
            final Matcher matcher = TAG_REGEX.matcher(axioms.get(axiomIndex).toString());

            while (matcher.find()) {
                tagValues.add(matcher.group(1));


            }
            if(tagValues.size()>1) {
                for (int relationIndex = 0; relationIndex < tagValues.size(); ++relationIndex) {
                    if (str.equals(tagValues.get(relationIndex))) {
                        for(int i =0 ;i< tagValues.size(); ++i) {
                            if(i!=relationIndex) {
                                List<Integer>relatedOffsets = getOffsetIfExists(words,tagValues.get(i));
                                if(relatedOffsets.size()>0) {
                                    for(int j = 0; j< relatedOffsets.size(); ++j) {
                                        Relation relation = new Relation();
                                        relation.setOffset1(word.getOffset());
                                        relation.setOffset2(relatedOffsets.get(j));
                                        relation.setRelationName(axioms.get(axiomIndex).getAxiomType().toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(tagValues);
            tagValues.clear();
        }

        return relations;
    }

    List<Integer> getOffsetIfExists(List<Word> words, String word){
        List<Integer> response = new ArrayList<>();
        for(int i = 0; i<words.size();++i){
            if(word.toLowerCase().equals(words.get(i).getNormalizeForm().toLowerCase())){
                response.add(i);
            }
        }
        return response;
    }

}

