package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.List;

import com.example.parser.ParseException;
import com.example.parser.PasswordPolicyParser;

/**
 * パスワードを生成するコントロールクラスです。
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordController {

    /**
     * 新規のPasswordContollerを構築します。
     */
    public PasswordController() {
    }

    /**
     * パスワードを生成する処理をコントロールします。
     * 
     * @param args
     *            - コマンドライン引数
     */
    public void run(String... args) {
        MessageUtil util = new ConsoleMessageUtil();
        try {
            Policy policy = new PasswordPolicyParser().parse(args);
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            PasswordGenerator generator = new PasswordGenerator(policy, secureRandom);
            String password;

            // 記号を許可する場合は禁則文字をユーザーに確認する
            if (policy.isAcceptSymbolChar()) {
                inputProhibitionChars(generator);
            }
            password = generator.generatePassword();
            util.outputMessage(password);
        } catch (ParseException e) {
            List<String> errors = e.getErrors();
            String newLineCode = SystemValue.toMessage(SystemValueType.newLineCode);
            StringBuilder message = new StringBuilder();
            for (String s : errors) {
                message.append(s);
                if (!s.equals(errors.get(errors.size() - 1))) {
                    message.append(newLineCode);
                }
            }
            util.outputError(message.toString());
        } catch (NoSuchAlgorithmException e) {
            util.outputError(
                    MessageFormat.format(Messages.getMessages(MessageType.ERRMSG_NO_SUCH_ALGORITHM), e.getMessage()));
        } catch (GeneratePasswordAppException e) {
            util.outputError(e.getMessage());
        }
    }

    private void inputProhibitionChars(PasswordGenerator generator) {
        ConsoleMessageUtil util = new ConsoleMessageUtil();
        util.outputMessage(Messages.getMessages(MessageType.INFMSG_DEFAULT_SPETIAL_CHARS));
        util.outputMessage(SystemValue.toMessage(SystemValueType.defaultAcceptedSymbolChars));
        util.outputMessage(Messages.getMessages(MessageType.INFMSG_IGNORE_SPETIAL_CHAR_OPERATION));
        try (InputStreamReader s = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(s)) {
            char[] retChars = br.readLine().toCharArray();
            generator.setProhibitionChars(retChars);

        } catch (IOException e) {
            // システム自体に問題がある場合に発生するエラーのため、アプリケーションエラーとしてthrowする
            throw new GeneratePasswordAppException(e);
        }

    }

}
