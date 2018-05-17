package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemValue {

    private static final String INT_PROP_ERRMSG = "設定値[{0}]の値はint型でなくてはなりません。設定ファイルを確認してください。";
    private static final String PROP_FILE_NAME = "SystemValue";
    private static final String MISSING_PROP_MSG = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

    private static ResourceBundle BUNDLE;

    static {
        try {
            BUNDLE = ResourceBundle.getBundle(PROP_FILE_NAME);
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, e.getMessage()));
        }
    }

    public SystemValue() {
        throw new UnsupportedOperationException("this is static class.");
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を数値で返します。
     * 
     * @return 数値型のプロパティ値
     * @throws 取得したプロパティ値が数値に変換できない場合
     */
    public static int toNumber(SystemValueType sType) {
        try {
            return Integer.parseInt(toMessage(sType));
        } catch (NumberFormatException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(INT_PROP_ERRMSG, sType.name()), e);
        }
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を真偽値で返します。
     * 
     * @return 真偽値型のプロパティ値
     */
    public static boolean toBoolean(SystemValueType sType) {
        return Boolean.parseBoolean(toMessage(sType));
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を文字列で返します。
     * 
     * @return 文字列型のプロパティ値
     * @throws プロパティファイルまたはプロパティが参照できない場合
     */
    public static String toMessage(SystemValueType sType) {
        try {
            return BUNDLE.getString(sType.name());
        } catch (MissingResourceException e) {
            String missingObjectName = "";
            if (e.getKey() != "") {
                missingObjectName = sType.name();
            } else {
                missingObjectName = PROP_FILE_NAME;
            }
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, missingObjectName), e);
        }
    }

}
