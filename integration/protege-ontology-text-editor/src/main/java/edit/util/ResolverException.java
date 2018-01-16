package edit.util;

public class ResolverException extends Exception {
    private String name;
    private String type;

    public ResolverException(String name, String type) {
        super("Could not resolve name " + name + " of type " + type + " in ontology.");
        this.name = name;
        this.type = type;
    }
}
