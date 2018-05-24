package com.example.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.example.Policy;

public class PasswordPolicyParserTest {
    private PasswordPolicyParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new PasswordPolicyParser();
    }

    @Test
    public void parseWithsTest() {
        Policy policy = new Policy(64, 1, true);
        Policy actualPolicy = parser.parse(generateParseArgs("64", "1", "-s"));
        assertThat(actualPolicy.getPasswordLength(), is(policy.getPasswordLength()));
        assertThat(actualPolicy.getLetterCase(), is(policy.getLetterCase()));
        assertThat(actualPolicy.isAcceptSymbolChar(), is(policy.isAcceptSymbolChar()));
    }

    @Test
    public void parseWithSTest() {
        Policy policy = new Policy(64, 1, false);
        Policy actualPolicy = parser.parse(generateParseArgs("64", "1", "-S"));
        assertThat(actualPolicy.getPasswordLength(), is(policy.getPasswordLength()));
        assertThat(actualPolicy.getLetterCase(), is(policy.getLetterCase()));
        assertThat(actualPolicy.isAcceptSymbolChar(), is(policy.isAcceptSymbolChar()));
    }

    @Test
    public void parseWithoutSTest() {
        Policy policy = new Policy(64, 1, true);
        Policy actualPolicy = parser.parse(new String[] { "-l", "64", "-c", "1" });
        assertThat(actualPolicy.getPasswordLength(), is(policy.getPasswordLength()));
        assertThat(actualPolicy.getLetterCase(), is(policy.getLetterCase()));
        assertThat(actualPolicy.isAcceptSymbolChar(), is(policy.isAcceptSymbolChar()));
    }

    @Test(expected = ParseException.class)
    public void lengthIsNotIntTest() {
        parser.parse(generateParseArgs("aaa", "2", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void caseIsNotIntTest() {
        parser.parse(generateParseArgs("16", "bbb", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void lengthIsOverTest() {
        parser.parse(generateParseArgs("65", "2", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void lengthIsUnderTest() {
        parser.parse(generateParseArgs("0", "2", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void caseIsOverTest() {
        parser.parse(generateParseArgs("16", "4", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void caseIsUnderTest() {
        parser.parse(generateParseArgs("16", "-1", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void illegalArgsTest() {
        parser.parse(new String[] { "-l", "16", "-c", "3", "-b" });
        fail("Expected exception is not catched.");

    }

    @Test(expected = ParseException.class)
    public void mismatchParamTest() {
        parser.parse(new String[] { "-l" });
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void multiErrorTest() {
        parser.parse(generateParseArgs("xxx", "yyy", "-s"));
        fail("Expected exception is not catched.");
    }

    @Test(expected = ParseException.class)
    public void getOthersTest() {
        parser.parse(new String[] { "-l", "10", "-c", "3", "-s", "aaa", "bbb" });
        fail("Expected exception is not catched.");
    }

    private String[] generateParseArgs(String passwordLength, String letterCase, String acceptSymbolChar) {
        return new String[] { "-l", passwordLength, "-c", letterCase, acceptSymbolChar };
    }

}
