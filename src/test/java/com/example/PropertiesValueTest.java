package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PropertiesValueTest {

    @Test
    public void getPropertiesValueTest() {
        PropertiesValueProvider propertiesValueProvider = PropertiesValue.getPropertiesValue();
        assertThat(propertiesValueProvider.getDefaultLength(), is(8));
        assertThat(PropertiesValue.getPropertiesValue().getDefaultLength(), is(8));
        assertThat(propertiesValueProvider, is(PropertiesValue.getPropertiesValue()));
    }

    @Test
    public void getPropertiesMessageTest() {
        PropertiesMessageProvider propertiesMessageProvider = PropertiesValue.getPropertiesMessage();
        assertThat(propertiesMessageProvider.getMessage(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS),
                is("引数の後にパラメータがありません。引数とパラメータを正しく組み合わせて指定してください。 : {0}"));
        assertThat(PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS),
                is("引数の後にパラメータがありません。引数とパラメータを正しく組み合わせて指定してください。 : {0}"));
        assertThat(propertiesMessageProvider, is(PropertiesValue.getPropertiesMessage()));
    }

}
