package com.example;

import static org.junit.Assert.fail;

import org.junit.Test;

public class GeneratePasswordTest {
    @Test(expected = UnsupportedOperationException.class)
    public void newInstanceTest() throws UnsupportedOperationException {
        new GeneratePassword();
        fail("想定したエラーが発生しませんでした。");
    }

    @Test
    public void test() {
        GeneratePassword.main(new String[] { "-l", "16", "-c", "1", "-S" });
    }
}
