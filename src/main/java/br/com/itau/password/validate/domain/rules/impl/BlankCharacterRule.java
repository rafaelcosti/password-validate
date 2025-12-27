package br.com.itau.password.validate.domain.rules.impl;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;

public class BlankCharacterRule implements ValidationRule {

    private static final String BLANK_CHARACTER = " ";
    private static final String MESSAGE = "A senha contém caracteres em branco";
    private static final String CODE = "PWD0006";

    @Override
    public void validate(String value) {
        if (value.contains(BLANK_CHARACTER)){
            throw new PasswordInvalidException(MESSAGE, CODE);
        }
    }
}
