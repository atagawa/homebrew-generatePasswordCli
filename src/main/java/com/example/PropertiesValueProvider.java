package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * propertiesファイルで定義されたシステム用の値を提供するクラスです。
 * 
 * @author atagawa
 * @since 0.0.1d 2018/05/23
 */
public class PropertiesValueProvider implements SystemValueProvider {

    private final String INT_PROP_ERRMSG = "設定値[{0}]の値はint型でなくてはなりません。設定ファイルを確認してください。";
    private final static String PROP_FILE_NAME = "com.example.SystemValue";
    private final String MISSING_PROP_MSG = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

    private ResourceBundle bundle;

    /**
     * 新規のPropertiesValueProviderを構築します。
     */
    public PropertiesValueProvider() {
        this(PROP_FILE_NAME);
    }

    /**
     * propertiesファイルの基底名を指定してPropertiesValueProviderを構築します。
     * 
     * @param baseName
     *            - パッケージ名を含むpropertiesファイルの基底名
     */
    public PropertiesValueProvider(String baseName) {
        try {
            bundle = ResourceBundle.getBundle(baseName);
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, e.getMessage()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getMaxLength()
     */
    @Override
    public int getMaxLength() {
        return toNumber(SystemValueType.maxLength);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getMinLength()
     */
    @Override
    public int getMinLength() {
        return toNumber(SystemValueType.minLength);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getMinLength()
     */
    @Override
    public int getMinOfLetterCase() {
        return toNumber(SystemValueType.minOfLetterCase);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getMaxOfLetterCase()
     */
    @Override
    public int getMaxOfLetterCase() {
        return toNumber(SystemValueType.maxOfLetterCase);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getOutputForBlack()
     */
    @Override
    public String getOutputForBlack() {
        return toMessage(SystemValueType.outputForBlack);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getOutputForRed()
     */
    @Override
    public String getOutputForRed() {
        return toMessage(SystemValueType.outputForRed);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getDefaultLength()
     */
    @Override
    public int getDefaultLength() {
        return toNumber(SystemValueType.defaultLength);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getDefaultLetterCase()
     */
    @Override
    public int getDefaultLetterCase() {
        return toNumber(SystemValueType.defaultLetterCase);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getDefaultAcceptSymbolChars()
     */
    @Override
    public boolean getDefaultAcceptSymbolChars() {
        return toBoolean(SystemValueType.defaultAcceptSymbolChar);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.SystemValueProvider#getDefaultAcceptedSymbolChars()
     */
    @Override
    public String getDefaultAcceptedSymbolChars() {
        return toMessage(SystemValueType.defaultAcceptedSymbolChars);
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を数値で返します。
     * 
     * @return 数値型のプロパティ値
     * @throws 取得したプロパティ値が数値に変換できない場合
     */
    private int toNumber(SystemValueType sType) {
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
    private boolean toBoolean(SystemValueType sType) {
        return Boolean.parseBoolean(toMessage(sType));
    }

    /**
     * 列挙型の値でプロパティを参照し、結果を文字列で返します。
     * 
     * @return 文字列型のプロパティ値
     * @throws プロパティファイルまたはプロパティが参照できない場合
     */
    private String toMessage(SystemValueType sType) {
        try {
            return bundle.getString(sType.name());
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, sType.name()), e);
        }
    }
}
