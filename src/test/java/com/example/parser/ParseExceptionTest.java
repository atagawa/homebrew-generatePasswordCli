package com.example.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParseExceptionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listArgsException() {
        try {

            List<String> list = new ArrayList<>();
            list.add("test1");
            list.add("test2");
            list.add("test3");

            throw new ParseException(list);
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("test1"));
            assertThat(e.getErrors().get(1), is("test2"));
            assertThat(e.getErrors().get(2), is("test3"));
        }
    }

}
