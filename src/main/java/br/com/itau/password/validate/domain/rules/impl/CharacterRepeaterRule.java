package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class CharacterRepeaterRule implements ValidationRule {

    private static final String MESSAGE = "A senha não pode conter caracteres repetidos";
    private static final String CODE = "PWD0007";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);
        long distinctCount = value.chars().distinct().count();

        if (distinctCount != value.length()) {
            throw new PasswordInvalidException(MESSAGE, CODE);
        }
    }
}
