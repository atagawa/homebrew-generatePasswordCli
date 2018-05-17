package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordControllerTest {
    private PasswordController passwordController;
    private InputSnatcherForTest in = new InputSnatcherForTest();
    ByteArrayOutputStream out;
    PrintStream syserr;

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
        passwordController.run(new String[] { "-l", "20", "-c", "3", "-s", "false" });

    }

    @Test
    public void runWithSymbolCharTest() {
        in.inputln("!\"#$%&'()");
        passwordController.run(new String[] { "-l", "20", "-c", "3", "-s", "true" });

    }

    @Test
    public void inputProhibitionCharsTest() {
        try {
            in.inputln("!");
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            Class<?> controllerClass = passwordController.getClass();
            Method method = controllerClass.getDeclaredMethod("inputProhibitionChars",
                    new Class<?>[] { PasswordGenerator.class });
            method.setAccessible(true);
            method.invoke(passwordController, new Object[] { passwordGenerator });
            assertThat(String.valueOf(passwordGenerator.getProhibitionChars()), is("!"));
        } catch (NoSuchMethodException | SecurityException e) {
            fail("getDecleardMethod() throw this Exception.:" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            fail("system error.:" + e.getMessage());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            fail("invoke() throw this Exception.:" + e.getMessage());
        }
    }
}
