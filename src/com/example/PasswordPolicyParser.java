package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * パスワードパース用クラス
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 *
 */
public class PasswordPolicyParser {
    private static ResourceBundle rb = ResourceBundle.getBundle("Messages");

    private SecureRandom secureRandom;
    private int length;
    private int charCase;
    private boolean acceptSymbolChar;
    private String policy;

    private boolean hasError;
    private ArrayList<String> responseMessages;

    /**
     * パスワードポリシーパース用クラスのコンストラクタ
     */
    public PasswordPolicyParser() {
        this(Integer.parseInt(rb.getString("default.length")), Integer.parseInt(rb.getString("default.charCase")),
                Boolean.parseBoolean(rb.getString("default.acceptSymbolChar")));
    }

    /**
     * パスワードポリシーパース用クラスのコンストラクタ
     * 
     * @param length:int
     * @param charCase:int
     * @param acceptSymbolChar:boolean
     */
    public PasswordPolicyParser(int length, int charCase, boolean acceptSymbolChar) {
        try {
            this.length = length;
            this.charCase = charCase;
            this.acceptSymbolChar = acceptSymbolChar;
            this.secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(rb.getString("noSuchAlgorithmExceptionMessage"));
            e.printStackTrace();
        }
    }

    /**
     * policyをセットするメソッド
     * 
     * @param args：String[]
     */
    public void setPasswordPolicy(String... args) {
        handleArguments(args);
        StringBuilder policy = new StringBuilder();

        appendPolicy(policy, rb.getString("startOfNumericNumber").charAt(0), rb.getString("endOfNumericNumber").charAt(0));
        if (charCase == Integer.parseInt(rb.getString("charCaseUpperOnly"))
                || charCase == Integer.parseInt(rb.getString("charCaseEitherUpperLower"))) {
            appendPolicy(policy, rb.getString("startOfUpperCaseLetters").charAt(0), rb.getString("endOfUpperCaseLetters").charAt(0));
        }
        if (charCase == Integer.parseInt(rb.getString("charCaseLowerOnly"))
                || charCase == Integer.parseInt(rb.getString("charCaseEitherUpperLower"))) {
            appendPolicy(policy, rb.getString("startOfLowerCaseLetters").charAt(0), rb.getString("endOfLowerCaseLetters").charAt(0));
        }
        if (acceptSymbolChar) {
            appendPolicy(policy, rb.getString("default.acceptSpecialChars").toCharArray());
        }
        this.policy = policy.toString();
    }

    /**
     * policyをセットするメソッド
     * 
     * @param policy:StringBuilder
     * @param ignore:char[]
     */
    public void setPasswordPolicy(StringBuilder policy, char[] ignore) {
        for (int i = 0; i < ignore.length; i++) {
            int charIdx = policy.indexOf(String.valueOf((char) ignore[i]));
            if (charIdx != -1) {
                policy.deleteCharAt(charIdx);
            }
        }
        this.policy = policy.toString();
    }

    /**
     * policyを返すGetterメソッド
     * 
     * @return policy:String
     */
    public String getPasswordPolicy() {
        return policy;
    }

    // コマンドラインで取得した引数を分解し、エラーチェックおよび各変数にセットする
    private void handleArguments(String... args) {

        this.hasError = false;
        this.responseMessages = new ArrayList<String>();

        for (int i = 0; i < args.length; ++i) {
            try {
                if (rb.getString("lengthArgs").matches(args[i])) {
                    this.length = Integer.parseInt(args[++i]);
                    if (this.length > Integer.parseInt(rb.getString("maxLength"))) {
                        this.hasError = true;
                        this.responseMessages.add(MessageFormat.format(rb.getString("error.overPasswordLengthExceptionMessage"), args[i]));
                    }
                } else if (rb.getString("charCaseArgs").matches(args[i])) {
                    this.charCase = Integer.parseInt(args[++i]);
                    if (this.charCase >= Integer.parseInt(rb.getString("outOfCharCase"))) {
                        this.hasError = true;
                        this.responseMessages.add(MessageFormat.format(rb.getString("error.overRangeValueExceptionMessage"), args[i]));
                    }
                } else if (rb.getString("symbolArgs").matches(args[i])) {
                    this.acceptSymbolChar = Boolean.parseBoolean(args[++i]);
                } else {
                    this.hasError = true;
                    this.responseMessages.add(MessageFormat.format(rb.getString("error.unexpectedArgumentsExceptionMessage"), args[i]));
                }
            } catch (NumberFormatException e) {
                this.hasError = true;
                this.responseMessages.add(MessageFormat.format(rb.getString("error.numberFormatExceptionMessage"), args[i]));
            } catch (ArrayIndexOutOfBoundsException e) {
                this.hasError = true;
                this.responseMessages.add(MessageFormat.format(rb.getString("error.arrayIndexOutOfBoundsExceptionMessage"), args[--i]));
            }
        }
    }

    // ポリシーに文字列を追加する（連続したASCIIの始端終端を指定する）
    private StringBuilder appendPolicy(StringBuilder policy, int startChar, int endChar) {
        for (int i = startChar; i <= endChar; i++) {
            policy.append((char) i);
        }
        return policy;
    }

    // ポリシーに文字列を追加する（連続しないASCIIの配列を指定する
    private StringBuilder appendPolicy(StringBuilder policy, char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            policy.append((char) chars[i]);
        }
        return policy;
    }

    /**
     * SecureRandomオブジェクトを返すGetterメソッド
     * 
     * @return secureRandom:SecureRandom
     */
    public SecureRandom getSecureRandom() {
        return secureRandom;
    }

    /**
     * lengthを返すGetterメソッド
     * 
     * @return length:int
     */
    public int getLength() {
        return length;
    }

    /**
     * lengthをセットするSetterメソッド
     * 
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * charCaseを返すGetterメソッド
     * 
     * @return CharCase:int
     */
    public int getCharCase() {
        return charCase;
    }

    /**
     * charCaseをセットするSetterメソッド
     * 
     * @param charCase
     */
    public void setCharCase(int charCase) {
        this.charCase = charCase;
    }

    /**
     * acceptSymbolCharを返すGetterメソッド
     * 
     * @return acceptSymbolChar:boolean
     */
    public boolean isAcceptSymbolChar() {
        return acceptSymbolChar;
    }

    /**
     * acceptSymbolCharをセットするSetterメソッド
     * 
     * @param acceptSymbolChar
     */
    public void setAcceptSymbolChar(boolean acceptSymbolChar) {
        this.acceptSymbolChar = acceptSymbolChar;
    }

    /**
     * hasErrorを返すGetterメソッド
     * 
     * @return hasError:boolean
     */
    public boolean hasError() {
        return hasError;
    }

    /**
     * hasErrorセットするSetterメソッド
     * 
     * @param hasError:boolean
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * responseMesssageを返すGetterメソッド
     * 
     * @return responseMessage:ArrayList<String>
     */
    public ArrayList<String> getResponseMessage() {
        return responseMessages;
    }

    /**
     * responseMessageをセットするSetterメソッド
     * 
     * @param responseMessage:ArrayLisy<String>
     */
    public void setResponseMessage(ArrayList<String> responseMessage) {
        this.responseMessages = responseMessage;
    }
}
