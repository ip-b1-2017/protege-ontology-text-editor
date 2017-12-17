package com.fii.ai.view;

import com.fii.ai.view.DTO.Relation;
import com.fii.ai.view.DTO.Word;

import java.util.List;

/**
 * TODO: Retrieve all relations which contain the given word
 */
public class View {

    private List<Word> words;

    public List<Relation> getRelations(Word word){
       return null;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
