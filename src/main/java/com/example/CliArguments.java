package com.example;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

/**
 * コマンドライン引数を管理するクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 *
 */
public class CliArguments extends Policy {

    @Option(name = "-l", aliases = "--length", metaVar = "Password length", usage = "length")
    private int passwordLength;

    @Option(name = "-c", metaVar = "Charcter case (1:Upper only, 2:Lower only, 3:Either upper or lower)", usage = "char case")
    private int letterCase;

    @Option(name = "-s", metaVar = "Symbol char is accept(forbids -S)", usage = "symbol char", forbids = { "-S" })
    private boolean acceptSymbolChar;

    @Option(name = "-S", metaVar = "Symbol char is not accept(forbids -s)", usage = "symbol char")
    private boolean unacceptSymbolChar;

    @Argument
    private List<String> others = new ArrayList<>();

    /**
     * 記号を許可しない場合trueを返します。
     * 
     * @return 記号を許可しない(true)
     */
    public boolean isUnacceptSymbolChar() {
        return unacceptSymbolChar;
    }

}
