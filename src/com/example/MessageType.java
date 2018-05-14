package com.example;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * システムメッセージを管理する列挙型クラスです。
 * 
 * @author atagawa
 *
 */
public enum MessageType {
    INFMSG_DEFAULT_SPETIAL_CHARS,
    INFMSG_IGNORE_SPETIAL_CHAR_OPERATION,
    ERRMSG_INPUT_NON_SPECIAL_CHAR,
    ERRMSG_NUMBER_FORMAT,
    ERRMSG_NO_SUCH_ALGORITHM,
    ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS,
    ERRMSG_OVER_RANGE_VALUE,
    ERRMSG_OVER_PASSWORD_LENGTH,
    ERRMSG_UNEXPECTED_ARGUMENTS,
    ERRMSG_INPUT_INCOMPATIBLE_VALUE;

    private static final String properyFileName = "Messages";
    private static final String missingPropertyFileMsg = "設定ファイルまたは設定値が見つかりませんでした。:{0}";

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
