package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * コンソールへの出力をテストするクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 */
public class ConsoleOutputUtilTest {
    private OutputUtil util;
    ByteArrayOutputStream out;
    PrintStream sysout;
    PrintStream syserr;

    /**
     * テストコード前に実行する処理です。<br>
     * 標準出力を置き換えるためのオブジェクトを生成します。
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        sysout = new PrintStream(out);
        syserr = new PrintStream(out);
    }

    /**
     * 
     */
    @Test
    public void constructorNoArgsTest() {
        System.setOut(sysout);
        util = new ConsoleOutputUtil();
        String message = "Test message.";
        util.outputMessage(message);
        assertThat(out.toString(), is(message + System.lineSeparator()));
    }

    @Test
    public void outputMessageStringTest() {
        util = new ConsoleOutputUtil(sysout, syserr);
        String message = "Test message.";
        util.outputMessage(message);
        assertThat(out.toString(), is(message + System.lineSeparator()));
    }

    @Test
    public void outputErrorStringTest() {
        util = new ConsoleOutputUtil(sysout, syserr);
        String message = "Test message.";
        util.outputError(message);
        assertThat(out.toString(), is("red" + message + "black" + System.lineSeparator()));
    }
}
