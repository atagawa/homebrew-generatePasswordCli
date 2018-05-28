package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordControllerTest {
    private PasswordController passwordController;
    private InputSnatcherForTest in = new InputSnatcherForTest();
    ByteArrayOutputStream out;
    PrintStream syserr;
    OutputUtil util = new OutputUtil() {

        @Override
        public void outputMessage(String message) {
            System.out.println(message);

        }

        @Override
        public void outputError(String message) {
            System.err.println(message);
        }
    };

    @Before
    public void setUp() throws Exception {
        passwordController = new PasswordController();
        System.setIn(in);
        out = new ByteArrayOutputStream();
        syserr = new PrintStream(out);
    }

    @After
    public void after() {
        System.setIn(null);
    }

    @Test
    public void runTest() {
        passwordController.run(new String[] { "-l", "20", "-c", "3", "-S" });

    }

    @Test
    public void runWithSymbolCharTest() {
        in.inputln("!\"#$%&'()");
        passwordController.run(new String[] { "-l", "20", "-c", "3", "-s" });

    }

    @Test
    public void runWithExceptionTest() {
        passwordController.run(new String[] { "-l", "aaa", "-c" });
    }
}
