package temp;

import java.util.Random;

public class Relation {

    public Integer offset1;
    public Integer offset2;
    public String relation;

    public Relation(){

    }

    public Relation(Integer offset1,Integer offset2, String relation) {

        this.offset1 = offset1;
        this.offset2 = offset2;
        this.relation = relation;
    }

    public Relation(Integer offset1,Integer offset2){
        relation = "rel";
        for (int i=0; i< new Random().nextInt(20);i++){
            relation +=i;
        }

        this.offset1 = offset1;
        this.offset2 = offset2;
    }

}
