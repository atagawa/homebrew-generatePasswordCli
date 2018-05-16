package com.example;

public interface MessageUtil {

    /**
     * 通常のメッセージを出力します。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    void outputMessage(String message);

    /**
     * 通常のメッセージを列挙型より取得して出力します。
     * 
     * @param enumObj
     *            - 出力する列挙子
     */
    void outputMessage(MessageType enumObj);

    /**
     * エラー用のメッセージを出力します。<br>
     * コンソールには赤文字でメッセージを出力されます。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    void outputError(String message);

    /**
     * エラー用のコンソールメッセージを列挙型より取得して出力します。<br>
     * コンソールには赤文字でメッセージが出力されます。
     * 
     * @param mType
     *            - 出力する列挙子
     */
    void outputError(MessageType mType);

}