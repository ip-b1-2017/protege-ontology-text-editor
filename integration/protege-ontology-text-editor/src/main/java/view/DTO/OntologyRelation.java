package view.DTO;

/**
 * TODO:A DTO for storing a relation between two normalized words.
 */
public class OntologyRelation {
    private String startClass;
    private String property;
    private  String endClass;

    public String getStartClass() {
        return startClass;
    }

    public void setStartClass(String startClass) {
        this.startClass = startClass;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getEndClass() {
        return endClass;
    }

    public void setEndClass(String endClass) {
        this.endClass = endClass;
    }

    @Override
    public String toString() {
        return "OntologyRelation{" +
                "startClass='" + startClass + '\'' +
                ", property='" + property + '\'' +
                ", endClass='" + endClass + '\'' +
                '}';
    }

    public OntologyRelation(String startClass, String property, String endClass) {
        this.startClass = startClass;
        this.property = property;
        this.endClass = endClass;
    }

    public OntologyRelation() {
    }

}
