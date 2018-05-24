package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PropertiesValueProviderTest {

    @Test
    public void getMaxLengthTest() {
        assertThat(PropertiesValue.getPropertiesValue().getMaxLength(), is(64));
    }

    @Test
    public void getMinLengthTest() {
        assertThat(PropertiesValue.getPropertiesValue().getMinLength(), is(1));
    }

    @Test
    public void getMinOfLetterCaseTest() {
        assertThat(PropertiesValue.getPropertiesValue().getMinOfLetterCase(), is(0));
    }

    @Test
    public void getMaxOfLetterCaseTest() {
        assertThat(PropertiesValue.getPropertiesValue().getMaxOfLetterCase(), is(3));
    }

    @Test
    public void getOutputForBlackTest() {
        assertThat(PropertiesValue.getPropertiesValue().getOutputForBlack(), is("black"));
    }

    @Test
    public void getOutputForRedTest() {
        assertThat(PropertiesValue.getPropertiesValue().getOutputForRed(), is("red"));
    }

    @Test
    public void getDefaultLengthTest() {
        assertThat(PropertiesValue.getPropertiesValue().getDefaultLength(), is(8));
    }

    @Test
    public void getDefaultLetterCaseTest() {
        assertThat(PropertiesValue.getPropertiesValue().getDefaultLetterCase(), is(3));
    }

    @Test
    public void getDefaultAcceptSymbolCharsTest() {
        assertThat(PropertiesValue.getPropertiesValue().getDefaultAcceptSymbolChars(), is(true));
    }

    @Test
    public void getDefaultAcceptedSymbolCharsTest() {
        assertThat(PropertiesValue.getPropertiesValue().getDefaultAcceptedSymbolChars(), is("!\"#$%&'()"));
    }

    @Test(expected = GeneratePasswordAppException.class)
    public void missingResourceTest() {
        new PropertiesValueProvider("aaa");
        fail("Expected exception is not catched.");
    }

    @Test(expected = GeneratePasswordAppException.class)
    public void missingPropTest() {
        PropertiesValueProvider propertiesValueProvider = new PropertiesValueProvider("com.example.SystemValueTest");
        propertiesValueProvider.getDefaultLength();
        fail("Expected exception is not catched.");
    }

    @Test(expected = GeneratePasswordAppException.class)
    public void toNumberExceptionTest() {
        PropertiesValueProvider propertiesValueProvider = new PropertiesValueProvider("com.example.SystemValueTest");
        propertiesValueProvider.getDefaultLetterCase();
        fail("Expected exception is not catched.");
    }

}
