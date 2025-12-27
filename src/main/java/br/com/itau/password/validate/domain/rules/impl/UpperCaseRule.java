package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class UpperCaseRule implements ValidationRule {

    public UpperCaseRule(int upperCaseNumber) {
        this.upperCaseNumber = upperCaseNumber;
    }

    private final int upperCaseNumber;

    private static final String MESSAGE = "A senha deve conter ao menos %d letras maiúscula";
    private static final String CODE = "PWD0002";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);
        long count = value.chars().filter(Character::isUpperCase).count();

        if (count < this.upperCaseNumber) {
            throw new PasswordInvalidException(String.format(MESSAGE, this.upperCaseNumber), CODE);
        }
    }
}
