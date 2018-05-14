package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * システムで使用する値を管理する列挙型クラスです。
 * 
 * @author atagawa
 *
 */
public enum SystemValue {
    defaultLength,
    defaultLetterCase,
    defaultAcceptSymbolChar,
    defaultAcceptedSymbolChars,
    maxLength,
    outOfLetterCase,
    outputForRed,
    outputForBlack;

    private static final String missingPropertyMsg = "設定値[{0}]の値はint型でなくてはなりません。設定ファイルを確認してください。";
    private static final String properyFileName = "SystemValue";
    private static final String missingPropertyFileMsg = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

    /**
     * 列挙型の値でプロパティを参照し、結果を数値で返します。
     * 
     * @return 数値型のプロパティ値
     * @throws 取得したプロパティ値が数値に変換できない場合
     */
    public int toNumber() {
        try {
            return Integer.parseInt(toString());
        } catch (NumberFormatException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(missingPropertyMsg, name()), e);
        }
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を真偽値で返します。
     * 
     * @return 真偽値型のプロパティ値
     */
    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を文字列で返します。
     * 
     * @return 文字列型のプロパティ値
     * @throws プロパティファイルまたはプロパティが参照できない場合
     */
    @Override
    public String toString() {
        try {
            return ResourceBundle.getBundle(properyFileName).getString(name());
        } catch (MissingResourceException e) {
            String missingObjectName = "";
            if (e.getKey() != "") {
                missingObjectName = name();
            } else {
                missingObjectName = properyFileName;
            }
            throw new GeneratePasswordAppException(MessageFormat.format(missingPropertyFileMsg, missingObjectName), e);
        }
    }

}
