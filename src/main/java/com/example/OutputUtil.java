package com.example;

/**
 * メッセージの出力を管理するクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 *
 */
public interface OutputUtil {

    /**
     * 通常のメッセージを出力します。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    void outputMessage(String message);

    /**
     * エラー用のメッセージを出力します。<br>
     * コンソールには赤文字でメッセージが出力されます。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    void outputError(String message);
}