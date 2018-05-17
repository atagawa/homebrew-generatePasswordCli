package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SystemValueTest {

    @Test
    public void newInstanceTest() {
        try {
            new SystemValue();
        } catch (UnsupportedOperationException e) {
            assertThat(e.getMessage(), is("this is static class."));
        }
    }

    @Test
    public void getSystemValueToMessageTest() {
        String actual = SystemValue.toMessage(SystemValueType.newLineCode);
        assertThat(actual, is("\n"));
    }

    @Test
    public void getSystemValueToNumberTest() {
        int actual = SystemValue.toNumber(SystemValueType.defaultLength);
        assertThat(actual, is(8));
    }

    @Test
    public void getSystemValueToBooleanTest() {
        boolean actual = SystemValue.toBoolean(SystemValueType.defaultAcceptSymbolChar);
        assertThat(actual, is(true));
    }

}
