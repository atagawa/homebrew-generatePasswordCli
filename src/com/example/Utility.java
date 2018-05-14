package com.example;

import java.text.MessageFormat;

/**
 * メッセージ出力を管理するUtilityクラスです。
 * 
 * @author atagawa
 *
 */
public class Utility {

    /**
     * 新規のUtilityを構築します。
     */
    public Utility() {
    }

    /**
     * 通常のコンソールメッセージを出力します。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    public void outPutConsoleMessage(String message) {
        System.out.println(message);
    }

    /**
     * 通常のコンソールメッセージを列挙型より取得して出力します。
     * 
     * @param enumObj
     *            - 出力する列挙子
     */
    public void outPutConsoleMessage(Enum<?> enumObj) {
        System.out.println(enumObj);
    }

    /**
     * エラー用のコンソールメッセージを出力します。<br>
     * コンソールには赤文字でメッセージを出力されます。
     * 
     * @param message
     *            - 出力するメッセージ
     */
    public void outputErrorMessage(String message) {
        System.out.println(MessageFormat.format(getErrorOrnament(), message));
    }

    /**
     * エラー用のコンソールメッセージを列挙型より取得して出力します。<br>
     * コンソールには赤文字でメッセージが出力されます。
     * 
     * @param mType
     *            - 出力する列挙子
     */
    public void outputErrorMessage(MessageType mType) {
        System.out.println(MessageFormat.format(getErrorOrnament(), mType));
    }

    /**
     * エラーメッセージ表示用の接頭辞及び接尾辞をプロパティより取得し、MessageFormat#format()で使用できる形で返します。
     * 
     * @return 出力時に赤文字になる定義を付与したプレースホルダー付き文字列
     */
    private String getErrorOrnament() {
        return SystemValue.outputForRed.toString() + "{0}" + SystemValue.outputForBlack.toString();
    }
}
