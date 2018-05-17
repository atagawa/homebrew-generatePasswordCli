package com.example.parser;

import java.util.List;

/**
 * パスワードポリシーのパス時における例外をスローするためのクラスです。
 * 
 * @author atagawa
 *
 */
public class ParseException extends RuntimeException {

    /**
     * シリアルバージョンを示します。
     */
    private static final long serialVersionUID = 1L;
    private List<String> errors;

    /**
     * 指定されたエラーメッセージのリストを持つParseExceptionを構築します。
     * 
     * @param errors
     *            - エラーメッセージ(あとでParseException.getErrors()メソッドで取得できるように保存される)。
     */
    public ParseException(List<String> errors) {
        this.errors = errors;
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

}
