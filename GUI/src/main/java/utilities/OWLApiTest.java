package utilities;

import edit.edit.Api;
import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import validation.src.WordLemaTuple;
import view.DTO.Word;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Claudia Lucasi on 1/17/2018.
 */
public class OWLApiTest {
    private OWLApi testApi;
    private OWLReasoner reasoner;
    private OWLOntologyManager manager;
    private  Api editApi;
    private  OWLOntology ontology;
    private  List<WordLemaTuple> text = null;
    private  List<Word> viewText = new ArrayList<>();
    private OWLWorkspace workspace;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testApi = new OWLApi();
        workspace = new OWLWorkspace();
        workspace.setTitle("Test");
        try {
            ontology = load(manager);
        }catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        manager = null;
        ontology = null;
        workspace = null;
        testApi = null;
    }

    @org.junit.jupiter.api.Test
    void setText() {
    }

    @org.junit.jupiter.api.Test
    void getRelationsOfWord() {
    }

    @org.junit.jupiter.api.Test
    void isConcept() {
    }

    @org.junit.jupiter.api.Test
    void addRelation() {
    }

    @org.junit.jupiter.api.Test
    void addInstanceOf() {
    }

    @org.junit.jupiter.api.Test
    void removeConcept() {
    }

    private OWLOntology load(OWLOntologyManager manager) throws OWLOntologyCreationException {
        File file = new File("tmp/pizza.owl");
        return manager.loadOntologyFromOntologyDocument(file);
    }

}