package com.example;

import java.text.MessageFormat;

/**
 * コマンドライン引数で指定された文字列をPolicyとして振る舞えるようパースするクラスです。
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 *
 */
public class PasswordPolicyParser {

    /**
     * 新規のPasswordPolicyParserを構築します。
     */
    public PasswordPolicyParser() {
    }

    /**
     * コマンドラインで取得した引数を分解し、Policyオブジェクトに格納します。<br>
     * 引数に問題がある場合は、エラーメッセージとともに例外をスローします。
     * 
     * @param args
     *            - コマンドライン引数
     * @return 指定した値をポリシーとしてプロパティに持つPolicyオブジェクト
     * @throws GeneratePasswordAppException
     *             - 指定の引数に問題がある場合
     */
    public Policy parse(String... args) {
        Policy policy = new Policy();
        StringBuilder errMsgs = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            try {
                if ("-l".matches(args[i])) {
                    policy.setPasswordLength(Integer.parseInt(args[++i]));
                    if (policy.getPasswordLength() > SystemValue.maxLength.toNumber()) {
                        errMsgs.append(
                                MessageFormat.format(MessageType.ERRMSG_OVER_PASSWORD_LENGTH.toString(), args[i]));
                    }
                } else if ("-c".matches(args[i])) {
                    policy.setLetterCase(Integer.parseInt(args[++i]));
                    if (policy.getLetterCase() >= SystemValue.outOfLetterCase.toNumber()) {
                        errMsgs.append(MessageFormat.format(MessageType.ERRMSG_OVER_RANGE_VALUE.toString(), args[i]));
                    }
                } else if ("-s".matches(args[i])) {
                    policy.setAcceptSymbolChar(Boolean.parseBoolean(args[++i]));
                } else {
                    errMsgs.append(MessageFormat.format(MessageType.ERRMSG_UNEXPECTED_ARGUMENTS.toString(), args[i]));
                }
            } catch (NumberFormatException e) {
                errMsgs.append(MessageFormat.format(MessageType.ERRMSG_NUMBER_FORMAT.toString(), args[i]));
            } catch (ArrayIndexOutOfBoundsException e) {
                errMsgs.append(
                        MessageFormat.format(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS.toString(), args[--i]));
            }
        }
        if (errMsgs.length() != 0) {
            throw new GeneratePasswordAppException(errMsgs.toString());
        } else {
            return policy;
        }

    }
}
