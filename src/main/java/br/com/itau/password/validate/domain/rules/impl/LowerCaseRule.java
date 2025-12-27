package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class LowerCaseRule implements ValidationRule {

    public LowerCaseRule(int lowerCaseNumber) {
        this.lowerCaseNumber = lowerCaseNumber;
    }

    private final int lowerCaseNumber;

    private static final String MESSAGE = "A senha deve conter ao menos %d letras minúscula";
    private static final String CODE = "PWD0003";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);
        long count = value.chars().filter(Character::isLowerCase).count();

        if (count < this.lowerCaseNumber) {
            throw new PasswordInvalidException(String.format(MESSAGE, this.lowerCaseNumber), CODE);
        }
    }
}
