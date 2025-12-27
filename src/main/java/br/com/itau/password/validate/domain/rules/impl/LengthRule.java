package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class LengthRule implements ValidationRule {

    public LengthRule(int length) {
        this.length = length;
    }

    private final int length;

    private static final String MESSAGE = "A senha deve conter ao menos %d caracter(es)";
    private static final String CODE = "PWD0004";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);

        if (value.length() < this.length) {
            throw new PasswordInvalidException(String.format(MESSAGE, this.length), CODE);
        }
    }
}
