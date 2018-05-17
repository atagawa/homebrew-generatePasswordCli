package com.example.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    public void parseTest() {
        Policy policy = new Policy(64, 1, false);
        Policy actualPolicy = parser.parse(generateParseArgs("64", "1", "false"));
        assertThat(actualPolicy.getPasswordLength(), is(policy.getPasswordLength()));
        assertThat(actualPolicy.getLetterCase(), is(policy.getLetterCase()));
        assertThat(actualPolicy.isAcceptSymbolChar(), is(policy.isAcceptSymbolChar()));
    }

    @Test
    public void lengthIsNotIntTest() {
        try {
            parser.parse(generateParseArgs("aaa", "2", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-l, -cにはint型を指定してください。 : aaa"));
        }
    }

    @Test
    public void caseIsNotIntTest() {
        try {
            parser.parse(generateParseArgs("16", "bbb", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-l, -cにはint型を指定してください。 : bbb"));
        }
    }

    @Test
    public void lengthIsOverTest() {
        try {
            parser.parse(generateParseArgs("65", "2", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-lには1~64までの整数を指定してください。 : 65"));
        }
    }

    @Test
    public void lengthIsUnderTest() {
        try {
            parser.parse(generateParseArgs("0", "2", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-lには1~64までの整数を指定してください。 : 0"));
        }
    }

    @Test
    public void caseIsOverTest() {
        try {
            parser.parse(generateParseArgs("16", "4", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-cには0~3の整数を指定してください。 : 4"));
        }
    }

    public void caseIsUnderTest() {
        try {
            parser.parse(generateParseArgs("16", "-1", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-cには0~3の整数を指定してください。 : -1"));
        }
    }

    @Test
    public void illegalArgsTest() {
        try {
            parser.parse(new String[] { "-l", "16", "-c", "3", "-b" });
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("不明な引数が指定されました。 : -b"));
        }
    }

    @Test
    public void mismatchParamTest() {
        try {
            parser.parse(new String[] { "-l" });
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("引数の後にパラメータがありません。引数とパラメータを正しく組み合わせて指定してください。 : -l"));
        }
    }

    @Test
    public void multiErrorTest() {
        try {
            parser.parse(generateParseArgs("xxx", "yyy", "true"));
        } catch (ParseException e) {
            assertThat(e.getErrors().get(0), is("-l, -cにはint型を指定してください。 : xxx"));
            assertThat(e.getErrors().get(1), is("-l, -cにはint型を指定してください。 : yyy"));

        }
    }

    private String[] generateParseArgs(String passwordLength, String letterCase, String acceptSymbolChar) {
        return new String[] { "-l", passwordLength, "-c", letterCase, "-s", acceptSymbolChar };
    }

}
