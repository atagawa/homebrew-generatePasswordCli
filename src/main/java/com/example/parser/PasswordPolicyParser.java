package com.example.parser;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.GeneratePasswordAppException;
import com.example.MessageType;
import com.example.Messages;
import com.example.Policy;
import com.example.SystemValue;
import com.example.SystemValueType;

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
    public Policy parse(String... args) throws ParseException {
        Policy policy = new Policy();
        List<String> errMsgs = new ArrayList<>();
        // TODO 2個指定してもエラーにならない
        for (int i = 0; i < args.length; ++i) {
            try {
                if ("-l".matches(args[i])) {
                    policy.setPasswordLength(Integer.parseInt(args[++i]));
                    if (policy.getPasswordLength() > SystemValue.toNumber(SystemValueType.maxLength)) {
                        errMsgs.add(MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_OVER_PASSWORD_LENGTH),
                                args[i]));
                    }
                } else if ("-c".matches(args[i])) {
                    policy.setLetterCase(Integer.parseInt(args[++i]));
                    if (policy.getLetterCase() >= SystemValue.toNumber(SystemValueType.outOfLetterCase)) {
                        errMsgs.add(MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_OVER_RANGE_VALUE),
                                args[i]));
                    }
                } else if ("-s".matches(args[i])) {
                    policy.setAcceptSymbolChar(Boolean.parseBoolean(args[++i]));
                } else {
                    errMsgs.add(MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_UNEXPECTED_ARGUMENTS),
                            args[i]));
                }
            } catch (NumberFormatException e) {
                errMsgs.add(MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_NUMBER_FORMAT), args[i]));
            } catch (ArrayIndexOutOfBoundsException e) {
                errMsgs.add(MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS),
                        args[--i]));
            }
        }
        if (errMsgs.size() > 0) {
            throw new ParseException(errMsgs);
        }

        return policy;
    }
}
