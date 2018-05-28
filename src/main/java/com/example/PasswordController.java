package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * パスワードを生成するコントロールクラスです。
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordController {

    /**
     * パスワードを生成する処理をコントロールします。
     * 
     * @param args
     *            - コマンドライン引数
     */
    public void run(String... args) {
        OutputUtil util = new ConsoleOutputUtil();
        CliArguments policy = new CliArguments();
        CmdLineParser parser = new CmdLineParser(policy);
        try {
            parser.parseArgument(args);
            PasswordPolicyValidator validator = new PasswordPolicyValidator();
            if (!validator.validate(policy)) {
                PasswordGenerator generator = new PasswordGenerator(policy, SecureRandom.getInstanceStrong());
                // 記号を許可する場合は禁則文字をユーザーに確認する
                // if()
                if (!policy.isUnacceptSymbolChar()) {
                    inputProhibitionChars(generator);
                }
                util.outputMessage(generator.generatePassword());
            } else {
                List<String> errors = validator.getErrors();
                util.outputError("Errors : ");
                for (String s : errors) {
                    util.outputError(s);
                }
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            util.outputMessage("Usege : ");
            parser.printSingleLineUsage(System.out);
            util.outputMessage(System.lineSeparator());
            parser.printUsage(System.out);
        } catch (NoSuchAlgorithmException e) {
            util.outputError(MessageFormat.format(
                    PropertiesValue.getPropertiesMessage().getMessage(MessageType.ERRMSG_NO_SUCH_ALGORITHM),
                    e.getMessage()));
        } catch (GeneratePasswordAppException e) {
            util.outputError(e.getMessage());
        }
    }

    private void inputProhibitionChars(PasswordGenerator generator) {
        ConsoleOutputUtil util = new ConsoleOutputUtil();
        util.outputMessage(PropertiesValue.getPropertiesMessage().getMessage(MessageType.INFMSG_DEFAULT_SPETIAL_CHARS));
        util.outputMessage(PropertiesValue.getPropertiesValue().getDefaultAcceptedSymbolChars());
        util.outputMessage(
                PropertiesValue.getPropertiesMessage().getMessage(MessageType.INFMSG_IGNORE_SPETIAL_CHAR_OPERATION));
        try (InputStreamReader s = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(s)) {
            char[] retChars = br.readLine().toCharArray();
            generator.setProhibitionChars(retChars);

        } catch (IOException e) {
            // システム自体に問題がある場合に発生するエラーのため、アプリケーションエラーとしてthrowする
            throw new GeneratePasswordAppException(e);
        }

    }

}
