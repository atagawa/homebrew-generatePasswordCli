package com.example;

/**
 * パスワードを生成するメインクラス<br>
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
public class GeneratePassword {

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
     */
    public static void main(String[] args) {
        new PasswordController().run(args);
    }
}
