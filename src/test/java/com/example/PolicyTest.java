package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PolicyTest {
    private Policy policy;

    @Test
    public void newInstanceTest() {
        policy = new Policy(30, 1, false);
        assertThat(policy.getPasswordLength(), is(30));
        assertThat(policy.getLetterCase(), is(1));
        assertThat(policy.isAcceptSymbolChar(), is(false));
    }

    @Test
    public void newInstanceWithNoArgsTest() {
        policy = new Policy();
        assertThat(policy.getPasswordLength(), is(8));
        assertThat(policy.getLetterCase(), is(3));
        assertThat(policy.isAcceptSymbolChar(), is(true));
    }

    @Test
    public void getterSetterPasswordLength() {
        policy = new Policy();
        policy.setPasswordLength(40);
        assertThat(policy.getPasswordLength(), is(40));
    }

    @Test
    public void getterSetterLetterCase() {
        policy = new Policy();
        policy.setLetterCase(2);
        assertThat(policy.getLetterCase(), is(2));
    }

    @Test
    public void getterSetterAcceptSymbolChar() {
        policy = new Policy();
        policy.setAcceptSymbolChar(false);
        assertThat(policy.isAcceptSymbolChar(), is(false));
    }
}
