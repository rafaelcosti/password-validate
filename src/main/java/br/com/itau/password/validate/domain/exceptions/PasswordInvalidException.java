package br.com.itau.password.validate.domain.exceptions;

public class PasswordInvalidException extends RuntimeException {
    private final String code;

    public PasswordInvalidException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
