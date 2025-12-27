package br.com.itau.password.validate.domain.services;

import br.com.itau.password.validate.domain.rules.ValidationRule;

import java.util.Set;

public class PasswordValidatorService {

    public PasswordValidatorService(Set<ValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    private final Set<ValidationRule> validationRules;

    public void validate(String value) {
        this.validationRules.forEach(rule -> rule.validate(value));
    }
}
