package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ConsoleMessageUtilTest {
    private MessageUtil util;
    ByteArrayOutputStream out;
    PrintStream sysout;
    PrintStream syserr;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        sysout = new PrintStream(out);
        syserr = new PrintStream(out);
    }

    @SuppressWarnings("serial")
    @Test
    public void constructorNoArgsTest() {
        try {
            util = new ConsoleMessageUtil();
            Class<?> utilClass = util.getClass();
            Field fields[] = utilClass.getDeclaredFields();
            HashMap<String, Object> actual = new HashMap<>();
            HashMap<String, Object> expected = new HashMap<>() {
                {
                    put("out", System.out);
                }
                {
                    put("err", System.err);
                }
            };

            for (Field f : fields) {
                if (!f.isSynthetic()) {
                    f.setAccessible(true);
                    actual.put(f.getName(), f.get(util));
                }
            }
            assertThat(actual, is(expected));
        } catch (Exception e) {
            fail("Create new instance is failed.:" + e.getMessage());
        }
    }

    @SuppressWarnings("serial")
    @Test
    public void constructorWithArgsTest() {
        try {
            util = new ConsoleMessageUtil(sysout, syserr);
            Class<?> utilClass = util.getClass();
            Field fields[] = utilClass.getDeclaredFields();
            HashMap<String, Object> actual = new HashMap<>();
            HashMap<String, Object> expected = new HashMap<>() {
                {
                    put("out", sysout);
                }
                {
                    put("err", syserr);
                }
            };

            for (Field f : fields) {
                if (!f.isSynthetic()) {
                    f.setAccessible(true);
                    actual.put(f.getName(), f.get(util));
                }
            }
            assertThat(actual, is(expected));
        } catch (Exception e) {
            fail("Create new instance is failed.:" + e.getMessage());
        }
    }

    @Test
    public void outputMessageStringTest() {
        util = new ConsoleMessageUtil(sysout, syserr);
        String message = "Test message.";
        util.outputMessage(message);
        assertThat(out.toString(), is(message + System.lineSeparator()));
    }

    @Test
    public void outputMessageEnumTest() {
        util = new ConsoleMessageUtil(sysout, syserr);
        MessageType mType = MessageType.INFMSG_DEFAULT_SPETIAL_CHARS;
        util.outputMessage(mType);
        assertThat(out.toString(), is(Messages.getMessages(mType) + System.lineSeparator()));
    }

    @Test
    public void outputErrorStringTest() {
        util = new ConsoleMessageUtil(sysout, syserr);
        String message = "Test message.";
        util.outputError(message);
        assertThat(out.toString(), is("\u001b[0031m" + message + "\u001b[00m" + System.lineSeparator()));
    }

    @Test
    public void outputErrorEnumTest() {
        util = new ConsoleMessageUtil(sysout, syserr);
        MessageType mType = MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS;
        util.outputError(mType);
        assertThat(out.toString(),
                is("\u001b[0031m" + Messages.getMessages(mType) + "\u001b[00m" + System.lineSeparator()));
    }

}
