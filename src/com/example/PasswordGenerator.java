package com.example;

import java.security.SecureRandom;

/**
 * パスワード生成クラス
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordGenerator {

    /**
     * パスワード生成クラスのコンストラクタ
     */
    public PasswordGenerator() {
    }

    /**
     * ポリシーに沿ってパスワードを生成するメソッド
     * 
     * @param parser:PasswordPolicyParser
     * @return result:String パスワードを表す文字列
     */
    public String generatePassword(PasswordPolicyParser parser) {
        StringBuilder result = new StringBuilder();
        String policy = parser.getPasswordPolicy();
        SecureRandom secureRandom = parser.getSecureRandom();
        while (result.length() < parser.getLength()) {
            result.append(policy.charAt(Math.abs(secureRandom.nextInt()) % policy.length()));
        }
        return result.toString();
    }

}
