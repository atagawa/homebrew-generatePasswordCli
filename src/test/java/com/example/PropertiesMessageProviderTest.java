package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PropertiesMessageProviderTest {

    @Test
    public void getMessageTest() {
        String actualMessage = PropertiesValue.getPropertiesMessage()
                .getMessage(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS);
        assertThat(actualMessage, is("引数の後にパラメータがありません。引数とパラメータを正しく組み合わせて指定してください。 : {0}"));
    }

    @Test(expected = GeneratePasswordAppException.class)
    public void getMissingResourceTest() throws Exception {
        new PropertiesMessageProvider("aaa");
        fail("Expected Exception is not catched.");
    }

    @Test(expected = GeneratePasswordAppException.class)
    public void getMissingPropTest() {
        PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_NUMBER_FORMAT);
        fail("Expected Exception is not catched.");
    }

}