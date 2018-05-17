package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 実際にパスワード文字列の生成を行うクラスです。
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordGenerator {

    private Policy policy;
    private Random randomNumberGenerator;
    private char[] prohibitionChars;

    /**
     * 新規のPasswordGeneratorを構築します。
     */
    public PasswordGenerator() throws NoSuchAlgorithmException {
        this(new Policy(), SecureRandom.getInstanceStrong());
    }

    public PasswordGenerator(Policy policy, Random randomNumberGenerator) {
        this.policy = policy;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    /**
     * 擬似乱数を生成し、パスワードに利用可能なランダム文字列を返します。
     * 
     * @param policy
     *            - パスワードポリシーの情報を保持するPolicyインスタンス
     * @param randomNumberGenerator
     *            - 乱数ジェネレータ
     * @param prohibitionChars
     *            - パスワード文字列に含めない禁則文字（指定しないことも可能）
     * @return ポリシー及び乱数ジェネレータによって生成された文字列
     */
    public String generatePassword() {
        Set<Character> passwordSet = new HashSet<>();
        Map<LetterType, Integer> letterMap = new EnumMap<>(LetterType.class);
        letterMap.put(LetterType.ACCEPT_UPPER_ONLY, 1);
        letterMap.put(LetterType.ACCEPT_LOWER_ONLY, 2);
        letterMap.put(LetterType.ACCEPT_EITHER_UPPER_LOWER, 3);

        // ASCIIでの!から~までの連続した文字を処理する
        addAvailableChars(passwordSet, '0', '9');
        if (policy.getLetterCase() == letterMap.get(LetterType.ACCEPT_UPPER_ONLY)
                || policy.getLetterCase() == letterMap.get(LetterType.ACCEPT_EITHER_UPPER_LOWER)) {
            addAvailableChars(passwordSet, 'A', 'Z');
        }
        if (policy.getLetterCase() == letterMap.get(LetterType.ACCEPT_LOWER_ONLY)
                || policy.getLetterCase() == letterMap.get(LetterType.ACCEPT_EITHER_UPPER_LOWER)) {
            addAvailableChars(passwordSet, 'a', 'z');
        }
        if (policy.isAcceptSymbolChar()) {
            addAvailableChars(passwordSet,
                    SystemValue.toMessage(SystemValueType.defaultAcceptedSymbolChars).toCharArray());
        }

        if (this.prohibitionChars != null) {
            for (char c : prohibitionChars) {
                passwordSet.remove(c);
            }
        }
        Character[] charList = new Character[passwordSet.size()];
        passwordSet.toArray(charList);
        StringBuilder result = new StringBuilder();
        while (result.length() < policy.getPasswordLength()) {
            result.append(charList[Math.abs(randomNumberGenerator.nextInt()) % charList.length]);
        }
        return result.toString();
    }

    private Set<Character> addAvailableChars(Set<Character> set, char startChar, char endChar) {
        for (char c = startChar; c <= endChar; c++) {
            set.add(c);
        }
        return set;
    }

    private Set<Character> addAvailableChars(Set<Character> set, char... chars) {
        for (char c : chars) {
            set.add(c);
        }
        return set;
    }

    /**
     * @return the policy
     */
    public Policy getPolicy() {
        return policy;
    }

    /**
     * @return the randomNumberGenerator
     */
    public Random getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    /**
     * @return the prohibitionChars
     */
    public char[] getProhibitionChars() {
        return prohibitionChars;
    }

    /**
     * @param prohibitionChars
     *            the prohibitionChars to set
     */
    public void setProhibitionChars(char[] prohibitionChars) {
        this.prohibitionChars = prohibitionChars;
    }

}
