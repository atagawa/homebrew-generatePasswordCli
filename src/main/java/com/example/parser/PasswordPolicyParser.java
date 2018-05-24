package com.example.parser;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.example.CliArguments;
import com.example.MessageType;
import com.example.Policy;
import com.example.PropertiesValue;

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
     * @throws ParseException
     *             - 指定の引数に問題がある場合
     */
    public Policy parse(String... args) throws ParseException {
        CliArguments arguments = new CliArguments();
        CmdLineParser parser = new CmdLineParser(arguments);
        List<String> errMsgs = new ArrayList<>();
        Policy policy = new Policy();
        try {
            parser.parseArgument(args);

            int length = arguments.getLength();
            int charCase = arguments.getCharCase();
            boolean unacceptSymbol = arguments.isUnacceptSymbol();
            List<String> others = arguments.getOthers();

            if (length < PropertiesValue.getPropertiesValue().getMinLength()
                    || PropertiesValue.getPropertiesValue().getMaxLength() < length) {
                errMsgs.add(MessageFormat.format(
                        PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_OVER_PASSWORD_LENGTH),
                        length));
            } else {
                policy.setPasswordLength(length);
            }

            if (charCase < PropertiesValue.getPropertiesValue().getMinOfLetterCase()
                    || PropertiesValue.getPropertiesValue().getMaxOfLetterCase() < charCase) {
                errMsgs.add(MessageFormat.format(
                        PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_OVER_RANGE_VALUE),
                        charCase));
            } else {
                policy.setLetterCase(charCase);
            }
            if (unacceptSymbol) {
                policy.setAcceptSymbolChar(false);
            } else {
                policy.setAcceptSymbolChar(true);
            }

            if (others.size() > 0) {
                errMsgs.add(MessageFormat.format(
                        PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_UNEXPECTED_ARGUMENTS),
                        others.toString()));
            }
            if (errMsgs.size() > 0) {
                throw new ParseException(errMsgs, parser);
            }
            return policy;
        } catch (CmdLineException e) {
            errMsgs.add(e.getMessage());
            throw new ParseException(errMsgs, parser);
        }
    }
}
