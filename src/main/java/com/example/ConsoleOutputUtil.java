package com.example;

import java.io.PrintStream;
import java.text.MessageFormat;

/**
 * メッセージ出力を管理するUtilityクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 *
 */
public class ConsoleOutputUtil implements OutputUtil {

    private PrintStream out;
    private PrintStream err;

    /**
     * 新規のUtilityを構築します。
     */
    public ConsoleOutputUtil() {
        this(System.out, System.err);
    }

    /**
     * 出力方法を指定した新規のUtilityを構築します。
     * 
     * @param out
     *            - infoメッセージの出力形式
     * @param err
     *            - errorメッセージの出力方式
     */
    public ConsoleOutputUtil(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outPutConsoleMessage(java.lang.String)
     */
    @Override
    public void outputMessage(String message) {
        out.println(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outputErrorMessage(java.lang.String)
     */
    @Override
    public void outputError(String message) {
        err.println(MessageFormat.format(getErrorOrnament(), message));
    }

    /**
     * エラーメッセージ表示用の接頭辞及び接尾辞をプロパティより取得し、MessageFormat#format()で使用できる形で返します。
     * 
     * @return 出力時に赤文字になる定義を付与したプレースホルダー付き文字列
     */
    protected String getErrorOrnament() {
        return PropertiesValue.getPropertiesValue().getOutputForRed() + "{0}"
                + PropertiesValue.getPropertiesValue().getOutputForBlack();
    }

}
