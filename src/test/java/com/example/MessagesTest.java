package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessagesTest {

    @Test
    public void newInstanceTest() {
        try {
            new Messages();
        } catch (UnsupportedOperationException e) {
            assertThat(e.getMessage(), is("this is static class."));
        }
    }

    @Test
    public void getMessageTest() {
        String actualMessage = Messages.getMessages(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS);
        assertThat(actualMessage, is("引数の後にパラメータがありません。引数とパラメータを正しく組み合わせて指定してください。 : {0}"));
    }
}