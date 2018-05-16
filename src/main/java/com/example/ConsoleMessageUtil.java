package com.example;

import java.io.PrintStream;
import java.text.MessageFormat;

/**
 * メッセージ出力を管理するUtilityクラスです。
 * 
 * @author atagawa
 *
 */
public class ConsoleMessageUtil implements MessageUtil {

    private PrintStream out;
    private PrintStream err;

    /**
     * 新規のUtilityを構築します。
     */
    public ConsoleMessageUtil() {
        this(System.out, System.err);
    }

    public ConsoleMessageUtil(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outPutConsoleMessage(java.lang.Str ing)
     */
    @Override
    public void outputMessage(String message) {
        outMessage(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outPutConsoleMessage(java.lang.Enum)
     */
    @Override
    public void outputMessage(MessageType mType) {
        outMessage(Messages.getMessages(mType));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outputErrorMessage(java.lang.String)
     */
    @Override
    public void outputError(String message) {
        outError(MessageFormat.format(getErrorOrnament(), message));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.MessageUtil#outputErrorMessage(com.example.MessageType)
     */
    @Override
    public void outputError(MessageType mType) {
        outError(MessageFormat.format(getErrorOrnament(), Messages.getMessages(mType)));
    }

    /**
     * エラーメッセージ表示用の接頭辞及び接尾辞をプロパティより取得し、MessageFormat#format()で使用できる形で返します。
     * 
     * @return 出力時に赤文字になる定義を付与したプレースホルダー付き文字列
     */
    protected String getErrorOrnament() {
        return SystemValue.toMessage(SystemValueType.outputForRed) + "{0}"
                + SystemValue.toMessage(SystemValueType.outputForBlack);
    }

    protected void outMessage(String message) {
        out.println(message);
    }

    protected void outError(String message) {
        err.println(message);
    }

}
