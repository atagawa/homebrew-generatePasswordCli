package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GeneratePasswordTest {
    @Test
    public void newInstanceTest() {
        try {
            new GeneratePassword();
        } catch (UnsupportedOperationException e) {
            assertThat(e.getMessage(), is("this class is static."));
        }
    }

    @Test
    public void test() {
        GeneratePassword.main(new String[] { "-l", "16", "-c", "1", "-s", "false" });
    }

}
