package com.example;

/**
 * パスワードを生成するメインクラス
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class GeneratePassword {

    /**
     * パスワードを生成するアプリのエントリポイントです。
     * 
     * @param args
     *            - コマンドライン引数が格納されます。
     */
    public static void main(String[] args) {
        new PasswordController().run(args);
    }
}
