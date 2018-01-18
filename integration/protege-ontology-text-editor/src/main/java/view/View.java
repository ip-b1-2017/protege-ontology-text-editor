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
                       // System.out.println("property" + prop);
                   // System.out.println("property name" + prop.getNamedProperty());
                  //  System.out.println("inverse property" + prop.getInverseProperty());

                  //  System.out.println("Instaances of" + class1 + " have " + prop);
                    // System.out.println(restriction+ " " + df.getOWLObjectComplementOf(restriction));
                    //System.out.println(restriction +  " "+ df.getOWLObjectComplementOf(restriction).getOperand());
                }

        }

        return null;
    }


    public List<Relation> getAllRelations(Word word, OWLOntology ontology, List<Word> words) {


        List<Relation> relations = new ArrayList<>();

        String str = word.getNormalizeForm().toLowerCase();

        List<OntologyRelation> ontologyRelations = getAllRelatedStrings(str,ontology);
        for(int i = 0; i < ontologyRelations.size(); ++i) {
            List<Integer> offsets;

            if(ontologyRelations.get(i).getStartClass().toLowerCase().equals(str)){
              //  System.out.println(ontologyRelations.get(i).getStartClass());
               // System.out.println(ontologyRelations.get(i).getEndClass());

                offsets = getOffsetIfExists(words, ontologyRelations.get(i).getEndClass());

                    for(int j = 0; j < offsets.size(); ++j){
                      // System.out.println(offsets);
                        Relation relation = new Relation(ontologyRelations.get(i).getProperty(), word.getOffset(), offsets.get(j));
                        relations.add(relation);
                    }

            } else {

              //  System.out.println(ontologyRelations.get(i).getStartClass());
                offsets = getOffsetIfExists(words, ontologyRelations.get(i).getStartClass());
             //   System.out.println(offsets);
                for(int j = 0; j < offsets.size();++j){
                    Relation relation = new Relation(ontologyRelations.get(i).getProperty(),  offsets.get(j), word.getOffset());
                    relations.add(relation);
                }
            }

        }

        return relations;
    }


    List<Integer> getOffsetIfExists(List<Word> words, String word){

      //  System.out.println(words);
        List<Integer> response = new ArrayList<>();
        for(int i = 0; i<words.size();++i){
            if(word.toLowerCase().equals(words.get(i).getNormalizeForm().toLowerCase())){

                response.add(words.get(i).getOffset());
            }
        }
        return response;
    }
    List<OntologyRelation> getAllRelatedStrings(String str, OWLOntology ontology) {
        List<OntologyRelation> response = new ArrayList<>();
        List<OWLAxiom> axioms = new ArrayList<>(ontology.getAxioms());

        for (int axiomIndex = 0; axiomIndex < ontology.getAxiomCount(); ++axiomIndex) {


            final List<String> tagValues = new ArrayList<String>();
            final Matcher matcher = TAG_REGEX.matcher(axioms.get(axiomIndex).toString());
            while (matcher.find()) {
                tagValues.add(matcher.group(1));
            }
            if (tagValues.size() > 3) {


                if (str.equals(tagValues.get(0).toLowerCase()))
                    for (int j = 2; j < tagValues.size(); ++j) {
                        OntologyRelation relation = new OntologyRelation();
                        relation.setStartClass(tagValues.get(0));
                        relation.setProperty(tagValues.get(1));
                        relation.setEndClass(tagValues.get(j));
                        response.add(relation);
                    }

            } else {
                for (int relationIndex = 1; relationIndex < tagValues.size(); ++relationIndex) {
                    if (str.equals(tagValues.get(relationIndex))) {
                        OntologyRelation relation = new OntologyRelation();
                        relation.setStartClass(tagValues.get(0));
                        relation.setProperty(tagValues.get(1));
                        relation.setEndClass(tagValues.get(relationIndex));
                        response.add(relation);

                    }
                }
            }
        }
        return response;
    }


}

