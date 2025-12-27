package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class SpecialCharacterRule implements ValidationRule {

    public SpecialCharacterRule(String specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    private final String specialCharacters;

    private static final String MESSAGE = "A senha deve conter ao menos um destes caracteres especiais: %s";
    private static final String CODE = "PWD0005";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);

        boolean hasSpecialChar = value.chars()
                .anyMatch(character -> this.specialCharacters.indexOf(character) >= 0);

        if (!hasSpecialChar) {
            throw new PasswordInvalidException(String.format(MESSAGE, this.specialCharacters), CODE);
        }
    }
}
