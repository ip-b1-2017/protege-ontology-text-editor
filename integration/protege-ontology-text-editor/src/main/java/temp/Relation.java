package temp;


public class Relation {

    public Integer offset1;
    public Integer offset2;
    public String relation;

    public Relation(Integer offset1,Integer offset2, String relation) {

        this.offset1 = offset1;
        this.offset2 = offset2;
        this.relation = relation;
    }

    public Integer getOffset1() {
        return offset1;
    }

    public void setOffset1(Integer offset1) {
        this.offset1 = offset1;
    }

    public Integer getOffset2() {
        return offset2;
    }

    public void setOffset2(Integer offset2) {
        this.offset2 = offset2;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
