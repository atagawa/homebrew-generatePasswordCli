package com.example;

/**
 * パスワードを生成するメインクラスです。
 * 
 * @since 1.0.0d 2018/05/23
 * @author atagawa
 */
public class GeneratePassword {

    /**
     * 本クラスはstaticに使用するため、コンストラクタの使用は制限されています。
     * 
     * @throws UnsupportedOperationException
     *             - コンストラクタが呼び出された場合
     */
    public GeneratePassword() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("this class is static.");
    }

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
