package com.example;

/**
 * パスワードポリシーを保持するエンティティクラスです。
 * 
 * @author atagawa
 *
 */
public class Policy {

    /**
     * 新規のPolicyクラスを構築します。<br>
     * 引数なしの場合、デフォルト値を各プロパティに格納します。
     */
    public Policy() {
        this(SystemValue.toNumber(SystemValueType.defaultLength),
                SystemValue.toNumber(SystemValueType.defaultLetterCase),
                SystemValue.toBoolean(SystemValueType.defaultAcceptSymbolChar));
    }

    /**
     * 指定した値を初期値とするPolicyクラスを構築します。
     * 
     * @param passwordLength
     *            - パスワードの長さ
     * @param letterCase
     *            - 英字の種別。0（英字を許可しない）, 1（大文字のみ許可する）, 2（小文字のみ許可する）,
     *            3（大文字小文字混在を許可する）のみ指定可能
     * @param acceptSymbolChar
     *            - 記号を許可するかをtrue/falseで指定する
     */
    public Policy(int passwordLength, int letterCase, boolean acceptSymbolChar) {
        this.passwordLength = passwordLength;
        this.letterCase = letterCase;
        this.acceptSymbolChar = acceptSymbolChar;
    }

    /**
     * パスワードの長さ
     */
    private int passwordLength;
    /**
     * 英字の種別
     */
    private int letterCase;
    /**
     * 記号の指定
     */
    private boolean acceptSymbolChar;

    /**
     * パスワードの長さを取得します。
     * 
     * @return パスワードの長さ
     */
    public int getPasswordLength() {
        return passwordLength;
    }

    /**
     * パスワードの長さを設定します。
     * 
     * @param passwordLength
     *            - パスワードの長さ
     */
    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    /**
     * 英字の種別を取得します。
     * 
     * @return 英字の種別
     */
    public int getLetterCase() {
        return letterCase;
    }

    /**
     * 英字の種別を設定します。
     * 
     * @param letterCase
     *            - 英字の種別
     */
    public void setLetterCase(int letterCase) {
        this.letterCase = letterCase;
    }

    /**
     * 記号の指定を取得します。
     * 
     * @return 記号の指定
     */
    public boolean isAcceptSymbolChar() {
        return acceptSymbolChar;
    }

    /**
     * 記号の指定を設定します。
     * 
     * @param acceptSymbolChar
     *            - 記号の指定
     */
    public void setAcceptSymbolChar(boolean acceptSymbolChar) {
        this.acceptSymbolChar = acceptSymbolChar;
    }

}
