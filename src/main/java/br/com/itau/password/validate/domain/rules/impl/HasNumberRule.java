package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class HasNumberRule implements ValidationRule {

    public HasNumberRule(int digitNumber) {
        this.digitNumber = digitNumber;
    }

    private final int digitNumber;

    private static final String MESSAGE = "A senha deve conter ao menos %d digito";
    private static final String CODE = "PWD0008";

    @Override
    public void validate(String value) {
        new ValueNotNullOrRule().validate(value);
        long count = value.chars().filter(Character::isDigit).count();

        if (count < this.digitNumber) {
            throw new PasswordInvalidException(String.format(MESSAGE, this.digitNumber), CODE);
        }
    }
}
