package com.fii.ai.view.DTO;

/**
 * TODO: A class for storing a relation between two words
 */
public class Relation {
    private String relationName;
    private int offset;
    private boolean type;

    public Relation(String relationName, int offset, boolean type) {
        this.relationName = relationName;
        this.offset = offset;
        this.type = type;
    }

    public  void Relation(Relation relation){
        this.relationName=relation.relationName;
        this.offset=relation.offset;
        this.type=relation.type;

    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }




}
