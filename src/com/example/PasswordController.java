package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;

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
        Utility util = new Utility();
        try {
            PasswordPolicyParser parser = new PasswordPolicyParser();
            Policy policy = parser.parse(args);
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            String password = "";
            // 記号を許可する場合は禁則文字をユーザーに確認する
            if (policy.isAcceptSymbolChar()) {
                util.outPutConsoleMessage(MessageType.INFMSG_DEFAULT_SPETIAL_CHARS);
                util.outPutConsoleMessage(SystemValue.defaultAcceptedSymbolChars);
                util.outPutConsoleMessage(MessageType.INFMSG_IGNORE_SPETIAL_CHAR_OPERATION);
                try (InputStreamReader s = new InputStreamReader(System.in);
                        BufferedReader br = new BufferedReader(s)) {
                    char[] retChars = br.readLine().toCharArray();
                    password = new PasswordGenerator().generatePassword(policy, secureRandom, retChars);

                } catch (IOException e) {
                    // システム自体に問題がある場合に発生するエラーのため、アプリケーションエラーとしてthrowする
                    throw new GeneratePasswordAppException(e);
                }
            } else {
                password = new PasswordGenerator().generatePassword(policy, secureRandom);
            }
            System.out.println(password);
        } catch (NoSuchAlgorithmException e) {
            util.outputErrorMessage(
                    MessageFormat.format(MessageType.ERRMSG_NO_SUCH_ALGORITHM.toString(), e.getMessage()));
        } catch (GeneratePasswordAppException e) {
            util.outputErrorMessage(e.getMessage());
        }
    }
}
