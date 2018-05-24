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
public class CliArguments {
    @Option(name = "-l", aliases = "--length", metaVar = "Password length", usage = "length")
    private int length = 8;

    @Option(name = "-c", metaVar = "Charcter case (1:Upper only, 2:Lower only, 3:Either upper or lower)", usage = "char case")
    private int charCase = 3;

    @Option(name = "-s", metaVar = "Symbol char is accept(forbids -S)", usage = "symbol char", forbids = { "-S" })
    private boolean acceptSymbol;

    @Option(name = "-S", metaVar = "Symbol char is not accept(forbids -s)", usage = "symbol char")
    private boolean unacceptSymbol;

    @Argument
    private List<String> others = new ArrayList<>();

    /**
     * コマンドライン引数で指定された-lの値を返します。
     * 
     * @return -lで指定したパスワードの長さ
     */
    public int getLength() {
        return length;
    }

    /**
     * コマンドライン引数で指定した-cの値を返します。
     * 
     * @return -cで指定した英字の種別指定
     */
    public int getCharCase() {
        return charCase;
    }

    /**
     * コマンドライン引数で-Sが指定されたかを返します。
     * 
     * @return コマンドライン引数における-Sの有無
     */
    public boolean isUnacceptSymbol() {
        return unacceptSymbol;
    }

    /**
     * コマンドライン引数で指定した中で、定義されていないものを返します。
     * 
     * @return プログラム上定義されていないコマンドライン引数
     */
    public List<String> getOthers() {
        return others;
    }

}
