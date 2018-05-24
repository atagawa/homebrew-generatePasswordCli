package com.example;

/**
 * propertiesファイルの値を提供するクラスインスタンスを管理するクラスです。
 * 
 * @author atagawa
 * @since 1.0.0d 2018/05/23
 */
public class PropertiesValue {

    private static PropertiesValueProvider propertiesValue = null;
    private static PropertiesMessageProvider propertiesMessage = null;

    /**
     * 新規または既存のPropertiesValueProviderオブジェクトを返します。
     * 
     * @return 新規または既存のProperiesValueProviderオブジェクト
     */
    public static PropertiesValueProvider getPropertiesValue() {
        if (propertiesValue == null) {
            propertiesValue = new PropertiesValueProvider();
        }
        return propertiesValue;
    }

    /**
     * 新規または既存のPropertiesMessageProviderオブジェクトを返します。
     * 
     * @return 新規または既存のProperiesMessageProviderオブジェクト
     */
    public static PropertiesMessageProvider getPropertiesMessage() {
        if (propertiesMessage == null) {
            propertiesMessage = new PropertiesMessageProvider();
        }
        return propertiesMessage;
    }

}
