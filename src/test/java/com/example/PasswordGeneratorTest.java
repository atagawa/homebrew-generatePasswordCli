package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;

public class PasswordGeneratorTest {
    private PasswordGenerator passwordGenerator;

    @Before
    public void setUp() throws Exception {
        passwordGenerator = new PasswordGenerator();

    }

    @Test
    public void generatePasswordTest() {
        passwordGenerator.setProhibitionChars(new char[] { '*', '+', '-', '/' });
        passwordGenerator.generatePassword();
    }

    @Test
    public void getPolicyTest() {
        assertThat(passwordGenerator.getPolicy().getClass().getName(), is(Policy.class.getName()));
    }

    @Test
    public void getRandomNumberGeneratorTest() {
        assertThat(passwordGenerator.getRandomNumberGenerator().getClass().getName(), is(SecureRandom.class.getName()));
    }

    @Test
    public void gettersetterProhibitionCharsTest() {
        char[] chars = { 'a', 'b', 'c' };
        passwordGenerator.setProhibitionChars(chars);
        assertThat(passwordGenerator.getProhibitionChars(), is(chars));
    }
}
