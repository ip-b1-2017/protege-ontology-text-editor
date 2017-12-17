package edit;

import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLProperty;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Api {
    private OWLWorkspace workspace;
    private Resolver resolver;

    public Api(OWLWorkspace workspace){
        this.workspace = workspace;
        this.resolver = new Resolver(workspace);
    }

    public void addRelation(String word1, String word2, String relation){
        OWLEntity entity1 = resolver.ResolveEntity(word1);
        OWLEntity entity2 = resolver.ResolveEntity(word2);

        OWLProperty property = resolver.ResolveProperty(relation);

        linkOwlEntities(entity1, entity2, property);
    }

    private void linkOwlEntities(OWLEntity entity1, OWLEntity entity2, OWLProperty property) {
        throw new NotImplementedException();
    }
}
