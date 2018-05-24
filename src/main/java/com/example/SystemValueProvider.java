package com.example;

/**
 * システムで管理された値を取得するメソッドを持つインターフェースです。
 * 
 * @author atagawa
 * @since 0.0.1d 2018/05/23
 *
 */
public interface SystemValueProvider {
    /**
     * パスワードの最大長を返します。
     * 
     * @return パスワードの最大長
     */
    int getMaxLength();

    /**
     * パスワードの最小長を返します。
     * 
     * @return パスワードの最小長
     * 
     */
    int getMinLength();

    /**
     * 英字種別を指定する際の最小数を返します。
     * 
     * @return 英字種別を指定する際の最小数
     */
    int getMinOfLetterCase();

    /**
     * 英字種別を指定する際の最大数を返します。
     * 
     * @return 英字種別を指定する際の最大数
     */
    int getMaxOfLetterCase();

    /**
     * 黒字で出力するための修飾子を返します。
     * 
     * @return 黒字で出力するための修飾子
     */
    String getOutputForBlack();

    /**
     * 赤字で出力するための修飾子を返します。
     * 
     * @return 赤字で出力するための修飾子
     */
    String getOutputForRed();

    /**
     * 既定のパスワード長を返します。
     * 
     * @return 既定のパスワード長
     */
    int getDefaultLength();

    /**
     * 既定の英字種別を指定する数値を返します。
     * 
     * @return 既定の英字種別を指定する数値
     */
    int getDefaultLetterCase();

    /**
     * 既定の記号を許可するかの真偽値を返します。
     * 
     * @return 既定の記号を許可するかの真偽値
     */
    boolean getDefaultAcceptSymbolChars();

    /**
     * 既定で許可される記号を文字列で返します。
     * 
     * @return 既定で許可される記号を表す文字列
     */
    String getDefaultAcceptedSymbolChars();

}