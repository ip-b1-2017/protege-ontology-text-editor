package com.fii.ai.view;

import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Retrieve all relations which contain the given word
 */
public class View {

    private  ArrayList<Word> words = new ArrayList<Word>();

    public List<Relation> getRelations(Word word){
       return null;
    }

    public  ArrayList<Word> getWords() {
        return words;
    }

    public  void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void add(Word word){
        words.add(word);
    }

}

