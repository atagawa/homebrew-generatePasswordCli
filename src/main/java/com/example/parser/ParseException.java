package com.example.parser;

import java.util.List;

import org.kohsuke.args4j.CmdLineParser;

/**
 * パスワードポリシーのパース時における例外をスローするためのクラスです。
 * 
 * @author atagawa
 * @since 0.0.1d 2018/05/23
 *
 */
public class ParseException extends RuntimeException {

    /**
     * シリアルバージョンを示します。
     */
    private static final long serialVersionUID = 1L;
    private List<String> errors;
    private CmdLineParser parser;

    /**
     * 指定されたエラーメッセージのリストを持つParseExceptionを構築します。
     * 
     * @param errors
     *            - エラーメッセージ(あとでParseException.getErrors()メソッドで取得できるように保存される)。
     * @param parser
     *            -
     *            CmblineParserオブジェクト（あとでParseException.getParser()メソッドで取得できるように保存される）。
     */
    public ParseException(List<String> errors, CmdLineParser parser) {
        this.errors = errors;
        this.parser = parser;
        throw this;
    }

    /**
     * エラーメッセージを取得します。
     * 
     * @return エラーメッセージを持つリスト
     */
    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * コマンドライン引数を処理するCmdLineParserオブジェクトを返します。
     * 
     * @return コマンドライン引数を処理するCmdLineParserオブジェクト
     */
    public CmdLineParser getParser() {
        return this.parser;
    }

}
