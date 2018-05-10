package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * パスワードを生成するコントロールクラス<br>
 * 以下を引数に指定可能（指定しない場合はデフォルト値が適用される）<br>
 * <ul>
 * <li>パスワードの長さ：-l int（デフォルト値8）</li>
 * <li>英字の種類：-c int（0:英字なし, 1:大文字のみ許可, 2:小文字のみ許可, 3:大文字小文字混在を許可（デフォルト値））</li>
 * <li>記号の許可：-s boolean（true:記号を許可（デフォルト値）, false:記号を拒否）</li>
 * </ul>
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordController {

    /**
     * パスワードを生成するコントロールクラスのコンストラクタ
     */
    public PasswordController() {
    }

    /**
     * パスワードを生成する処理をコントロールするメソッド
     * 
     * @param args
     *            args:String[] -l length -c charCase -s acceptSymbolChar
     */
    public void run(String... args) {
        final char ALL_SPECIAL_CHAR_IS_OK = '1';
        final char SPECIFIED_CHAR_IS_OK = '2';
        final char SPECIFIED_CHAR_IS_NG = '3';
        final String BANNED_INPUT_CHARS = "([a-zA-Zz04-9])";
        final int IDX_OF_HOW_TO_HANDLE_SYMBOL_CHAR = 0;
        final int IDX_OF_START_ACTUAL_SYMBOL_CHAR = 1;

        ResourceBundle rb = ResourceBundle.getBundle("Messages");
        PasswordPolicyParser parser = new PasswordPolicyParser();
        parser.parse(args);

        if (parser.hasError()) {
            ArrayList<String> errors = parser.getResponseMessage();
            for (String error : errors) {
                System.err.println(rb.getString("outputForRed") + error);
            }
            System.out.print(rb.getString("outputForBlack"));
        } else {
            if (parser.isAcceptSymbolChar()) {
                System.out.println(rb.getString("defaultSpecialCharsInfo"));
                System.out.println(rb.getString("default.acceptSpecialChars"));
                System.out.println(rb.getString("ignoreSpecialCharsOperationInfo"));
                System.out.println(rb.getString("ignoreSpecialCharsOperationParamInfo"));
                System.out.println(rb.getString("igonoreSpecialCharsExample"));

                try (InputStreamReader stream = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(stream);) {
                    String retString = br.readLine();

                    Pattern pattern = Pattern.compile(BANNED_INPUT_CHARS);
                    Matcher matcher = pattern.matcher(retString);
                    if (matcher.find()) {
                        System.err.println(
                                MessageFormat.format(rb.getString("outputForRed") + rb.getString("error.inputNonSpecialChars"), matcher.group()));
                        System.out.print(rb.getString("outputForBlack"));
                        return;
                    } else {
                        char retPattern = retString.charAt(IDX_OF_HOW_TO_HANDLE_SYMBOL_CHAR);

                        switch (retPattern) {
                        case ALL_SPECIAL_CHAR_IS_OK:/* 特殊文字を全て使用可能 */
                            parser.setPasswordPolicy();
                            // no code and use default value
                            break;
                        case SPECIFIED_CHAR_IS_OK:/* 指定した文字のみ使用可能 */
                            parser.setAcceptedSpecialChars(retString.substring(IDX_OF_START_ACTUAL_SYMBOL_CHAR).toCharArray());
                            parser.setPasswordPolicy();
                            break;
                        case SPECIFIED_CHAR_IS_NG: /* 指定した文字を使用不可能にする */
                            parser.setPasswordPolicy();
                            parser.setPasswordPolicy(new StringBuilder(parser.getPasswordPolicy()),
                                    retString.substring(IDX_OF_START_ACTUAL_SYMBOL_CHAR).toCharArray());
                            break;
                        default:
                            System.err.println(MessageFormat.format(rb.getString("outputForRed") + rb.getString("error.inputIncompatibleValue"),
                                    retString.charAt(IDX_OF_HOW_TO_HANDLE_SYMBOL_CHAR)));
                            System.out.print(rb.getString("outputForBlack"));
                            return;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                parser.setPasswordPolicy();
            }
            String password = new PasswordGenerator().generatePassword(parser);
            System.out.println(password);
        }
    }
}
