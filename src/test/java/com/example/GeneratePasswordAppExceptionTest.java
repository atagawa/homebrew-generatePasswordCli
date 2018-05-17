package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GeneratePasswordAppExceptionTest {

    @Test
    public void messageArgsTest() {
        String message = "this is exception message.";
        try {
            throw new GeneratePasswordAppException(message);
        } catch (GeneratePasswordAppException e) {
            assertThat(e.getMessage(), is(message));
        }
    }

    @Test
    public void causeArgsTest() {
        Throwable throwable = new Throwable();
        try {
            throw new GeneratePasswordAppException(throwable);
        } catch (GeneratePasswordAppException e) {
            assertThat(e.getCause(), is(throwable));
        }
    }

    @Test
    public void messageCauseArgsTest() {
        String message = "this is exception message.";
        Throwable throwable = new Throwable();
        try {
            throw new GeneratePasswordAppException(message, throwable);
        } catch (GeneratePasswordAppException e) {
            assertThat(e.getMessage(), is(message));
            assertThat(e.getCause(), is(throwable));
        }
    }

}
