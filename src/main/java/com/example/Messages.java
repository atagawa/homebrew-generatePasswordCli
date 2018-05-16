package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

    private static ResourceBundle BUNDLE;
    private static final String PROP_FILE_NAME = "Messages";
    private static final String MISSING_PROP_MSG = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

    static {
        try {
            BUNDLE = ResourceBundle.getBundle(PROP_FILE_NAME);
        } catch (MissingResourceException e) {
            throw new GeneratePasswordAppException(MessageFormat.format(MISSING_PROP_MSG, e.getMessage()));
        }
    }

    public Messages() {
        throw new UnsupportedOperationException("this is static class.");
    }

    public static String getMessages(MessageType mType) {
        return BUNDLE.getString(mType.name());
    }

}
