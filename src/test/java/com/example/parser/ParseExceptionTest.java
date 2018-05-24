package com.example.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineParser;

import com.example.CliArguments;

public class ParseExceptionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void listArgsException() {
        String[] messages = { "test1", "test2", "test3" };
        try {
            CliArguments args = new CliArguments();
            CmdLineParser parser = new CmdLineParser(args);
            List<String> list = new ArrayList<>();
            list.add(messages[0]);
            list.add(messages[1]);
            list.add(messages[2]);
            throw new ParseException(list, parser);
        } catch (ParseException e) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(stream);
            assertThat(e.getErrors().get(0), is(messages[0]));
            assertThat(e.getErrors().get(1), is(messages[1]));
            assertThat(e.getErrors().get(2), is(messages[2]));
            e.getParser().printSingleLineUsage(out);
            assertThat(stream.toString(), is(
                    " [VAL ...] [-S Symbol char is not accept(forbids -s)] [-c Charcter case (1:Upper only, 2:Lower only, 3:Either upper or lower)] [-l (--length) Password length] [-s Symbol char is accept(forbids -S)]"));
        }
    }

}
