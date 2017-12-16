package com.fii.ai.view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ViewTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void test1(){
        View view = new View();
        view.method();
    }
}

