package view.DTO;

/**
 * TODO: A class for storing a relation between two words
 */
public class Relation {
    private String relationName;
    private int offset1;
    private int offset2;

    public Relation() {
    }

    public Relation(String relationName, int offset1, int offset2) {

        this.relationName = relationName;
        this.offset1 = offset1;
        this.offset2 = offset2;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public int getOffset1() {
        return offset1;
    }

    public void setOffset1(int offset1) {
        this.offset1 = offset1;
    }

    public int getOffset2() {
        return offset2;
    }

    public void setOffset2(int offset2) {
        this.offset2 = offset2;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "relationName='" + relationName + '\'' +
                ", offset1=" + offset1 +
                ", offset2=" + offset2 +
                '}';
    }
}
