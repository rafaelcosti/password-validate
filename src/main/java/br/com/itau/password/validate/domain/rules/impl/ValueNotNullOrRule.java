package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class ValueNotNullOrRule implements ValidationRule {

    private static final String MESSAGE = "A senha não pode ser nula";
    private static final String CODE = "PWD0001";

    @Override
    public void validate(String value) {
        if (value == null) {
            throw new PasswordInvalidException(MESSAGE, CODE);
        }
    }
}
