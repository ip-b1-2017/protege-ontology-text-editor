package edit;

import org.protege.editor.owl.model.OWLWorkspace;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLProperty;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Resolver {
    private OWLWorkspace workspace;

    public Resolver(OWLWorkspace workspace) {
        this.workspace = workspace;
    }

    public OWLEntity ResolveEntity(String name){
        throw new NotImplementedException();
    }

    public OWLProperty ResolveProperty(String relation) {
        throw new NotImplementedException();
    }
}
