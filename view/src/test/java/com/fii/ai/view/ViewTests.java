package com.fii.ai.view;

import com.fii.ai.view.DTO.Word;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ViewTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void test1(){
        View view = new View();
    }

    @Test
    public void test2(){
        Word word1 = new Word("Americana","cea mai buna pizza",10);
        Word word3 = new Word("America","USA",150);
        View exemplu = new View();
        try {
            exemplu.add(word1);
            exemplu.add(word3);
            for(Word i:exemplu.getWords())
                System.out.println(i.getNormalizeForm() +' ' + i.getTextForm() + ' '+ i.getOffset());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

