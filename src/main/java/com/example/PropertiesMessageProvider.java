package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * propertiesファイルで定義されたシステム用のメッセージを提供するクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 */
public class PropertiesMessageProvider {

    private ResourceBundle bundle;
    private final static String PROP_FILE_NAME = "com.example.Messages";
    private final String MISSING_PROP_MSG = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

    /**
     * 新規のPropertiesMessageProviderを構築します。
     */
    public PropertiesMessageProvider() {
        this(PROP_FILE_NAME);
    }

    /**
     * propertiesファイルの基底名を指定して新規のPropertiesMessageProviderを構築します。
     * 
     * @param baseName
     *            - パッケージを含むプロパティファイルの基底名
     */
    public PropertiesMessageProvider(String baseName) {
        try {
            bundle = ResourceBundle.getBundle(baseName);
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, e.getMessage()));
        }
    }

    /**
     * 列挙型を指定してpropertiesファイルからメッセージを取得します。
     * 
     * @param mType
     *            - MessageType列挙型の要素
     * @return 指定された列挙型の名前属性に基づくpropertiesファイルの文字列値
     */
    public String getMessage(MessageType mType) {
        try {
            return bundle.getString(mType.name());
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, e.getMessage()));
        }
    }

}
