package com.example;

import java.security.NoSuchAlgorithmException;

/**
 * パスワードを生成するメインクラス<br>
 * 以下を引数に指定可能（指定しない場合はデフォルト値が適用される）<br>
 * <ul>
 * <li>パスワードの長さ：-l int（デフォルト値8）</li>
 * <li>英字の種類：-c int（0:英字なし, 1:大文字のみ許可, 2:小文字のみ許可, 3:大文字小文字混在を許可（デフォルト値））</li>
 * <li>記号の許可：-s boolean（true:記号を許可（デフォルト値）, false:記号を拒否）</li>
 * </ul>
 * 
 * @since 1.0 2018/05/08
 * @author atagawa
 *
 */
public class GeneratePassword {

    private static final String NUMBER_FORMAT_EXCEPTION_MSG = "-l, -cにはint型を指定してください。";
    private static final String EXCEPTION_MSG = "予期せぬエラーが発生しました。";
    private static final String NO_SUCH_ALGORITHM_EXCEPTION_MSG = "使用できないアルゴリズムが指定されました。";

    /**
     * パスワードを生成するメインメソッド<br>
     * 以下を引数に指定可能（指定しない場合はデフォルト値が適用される）<br>
     * <ul>
     * <li>パスワードの長さ：-l int（デフォルト値8）</li>
     * <li>英字の種類：-c int（0:英字なし, 1:大文字のみ許可, 2:小文字のみ許可, 3:大文字小文字混在を許可（デフォルト値））</li>
     * <li>記号の許可：-s boolean（true:記号を許可（デフォルト値）, false:記号を拒否）</li>
     * </ul>
     * 
     * @param args:String[]
     *            -l length -c charCase -s acceptSymbolChar
     * @throws GeneratePasswordException,NumberFormatException,
     *             Exception
     */
    public static void main(String[] args) {
        try {
            Password password = new Password();
            HandleArguments(password, args);
            password.GeneratePassword();
            System.out.println(password.getResult().toString());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            System.out.println(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (GeneratePasswordException generatePasswordException) {
            System.out.println(generatePasswordException);
            return;
        } catch (NumberFormatException numberFormatException) {
            System.out.println(NUMBER_FORMAT_EXCEPTION_MSG);
            return;
        } catch (Exception e) {
            System.out.println(EXCEPTION_MSG);
            System.out.println(e);
            return;
        }
    }

    private static void HandleArguments(Password password, String[] args) throws NumberFormatException, Exception {
        final int MAX_PASSWORD_LENGTH = 64;
        final int OUT_OF_CHAR_CASE = 4;
        final String LENGTH_ARGS = "-l";
        final String CHAR_CASE_ARGS = "-c";
        final String SYMBOL_ARGS = "-s";

        for (int i = 0; i < args.length; ++i) {
            if (LENGTH_ARGS.matches(args[i])) {
                password.setLength(Integer.parseInt(args[++i]));
                if (password.getLength() > MAX_PASSWORD_LENGTH) {
                    throw new OverPasswordLengthException();
                }
            } else if (CHAR_CASE_ARGS.matches(args[i])) {
                password.setCharCase(Integer.parseInt(args[++i]));
                if (password.getCharCase() >= OUT_OF_CHAR_CASE) {
                    throw new OverRangeValueException();
                }
            } else if (SYMBOL_ARGS.matches(args[i])) {
                password.setAcceptSymbolChar(Boolean.parseBoolean(args[++i]));
            } else {
                throw new UnexpectedArgumentsException();
            }
        }
    }
}
