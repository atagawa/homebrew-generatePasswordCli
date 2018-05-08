package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * パスワードの情報を持つエンティティクラス<br>
 * 
 * @since 1.0 2018/05/08
 * @author atagawa
 *
 */
public class Password {

    private SecureRandom secureRandom;
    private int length = 8;
    private int charCase = 3;
    private boolean acceptSymbolChar = true;
    private StringBuilder source;
    private StringBuilder result;

    /**
     * パスワードの情報を持つエンティティクラスを生成するコンストラクタ<br>
     * 
     * @throws NosuchAlgorithmException
     */
    public Password() throws NoSuchAlgorithmException {
        if (secureRandom == null) {
            this.secureRandom = SecureRandom.getInstanceStrong();
        }
        this.result = new StringBuilder();
    }

    /**
     * パスワードの文字列を生成するメソッド
     * 
     * @return result:String
     */
    public void GeneratePassword() {
        this.source = MakePasswordRule(charCase, acceptSymbolChar);
        while (this.result.length() < length) {
            // (intの乱数の絶対値%source配列の長さ)をインデックスに持つsourceの値をランダムな値としてAppendする
            this.result.append(source.charAt(Math.abs(secureRandom.nextInt()) % source.length()));
        }
    }

    private StringBuilder MakePasswordRule(int charCase, boolean acceptSymbolChar) {
        StringBuilder source = new StringBuilder();
        // 数字を許可
        AppendSource(source, 0x30, 0x39, new int[] {});
        if (charCase == 1 || charCase == 3) {
            // 英大文字を許可
            AppendSource(source, 0X41, 0x5A, new int[] {});
        }
        if (charCase == 2 || charCase == 3) {
            // 英小文字を許可
            AppendSource(source, 0x61, 0x7A, new int[] {});
        }
        if (acceptSymbolChar) {
            // 記号を許可（!#$%&()*+,-./）
            AppendSource(source, 0x21, 0x2f, new int[] { 0x22, 0x27 });
        }
        return source;
    }

    private StringBuilder AppendSource(StringBuilder source, int startChar, int endChar, int[] ignore) {
        for (int i = startChar; i <= endChar; i++) {
            source.append((char) i);
        }
        for (int i = 0; i < ignore.length; i++) {
            source.deleteCharAt(source.indexOf(String.valueOf((char) ignore[i])));
        }
        return source;
    }

    // getter, setter
    /**
     * SecureRandomオブジェクトを返すGetterメソッド
     * 
     * @return secureRandom:SecureRandom
     */
    public SecureRandom getSecureRandom() {
        return secureRandom;
    }

    /**
     * lengthを返すGetterメソッド
     * 
     * @return length:int
     */
    public int getLength() {
        return length;
    }

    /**
     * lengthをセットするSetterメソッド
     * 
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * charCaseを返すGetterメソッド
     * 
     * @return CharCase:int
     */
    public int getCharCase() {
        return charCase;
    }

    /**
     * charCaseをセットするSetterメソッド
     * 
     * @param charCase
     */
    public void setCharCase(int charCase) {
        this.charCase = charCase;
    }

    /**
     * acceptSymbolCharを返すGetterメソッド
     * 
     * @return acceptSymbolChar:boolean
     */
    public boolean isAcceptSymbolChar() {
        return acceptSymbolChar;
    }

    /**
     * acceptSymbolCharをセットするSetterメソッド
     * 
     * @param acceptSymbolChar
     */
    public void setAcceptSymbolChar(boolean acceptSymbolChar) {
        this.acceptSymbolChar = acceptSymbolChar;
    }

    /**
     * resultを返すGetterメソッド
     * 
     * @return result:StringBuilder
     */
    public StringBuilder getResult() {
        return result;
    }
}
