package com.example;

class GeneratePasswordException extends Exception {

    private static final long serialVersionUID = 1L;

    public GeneratePasswordException(String message) {
        super(message);
    }
}

class OverPasswordLengthException extends GeneratePasswordException {

    private static final long serialVersionUID = 1L;
    private static final String OVER_PASSWORD_LENGTH_EXCEPTION_MSG = "-lには64までの整数を指定してください。";

    public OverPasswordLengthException() {
        super(OVER_PASSWORD_LENGTH_EXCEPTION_MSG);
    }
}

class OverRangeValueException extends GeneratePasswordException {

    private static final long serialVersionUID = 1L;
    private static final String OVER_RANGE_VALUE_EXCEPTION_MSG = "-cには0〜3の整数を指定してください。";

    public OverRangeValueException() {
        super(OVER_RANGE_VALUE_EXCEPTION_MSG);

    }
}

class UnexpectedArgumentsException extends GeneratePasswordException {

    private static final long serialVersionUID = 1L;
    private static final String UNEXPECTED_ARGUMENTS_EXCEPTION_MSG = "不明な引数が指定されました。";

    public UnexpectedArgumentsException() {
        super(UNEXPECTED_ARGUMENTS_EXCEPTION_MSG);
    }
}