package br.com.itau.password.validate.ingfrastructure.configs;

import br.com.itau.password.validate.domain.rules.ValidationRule;
import br.com.itau.password.validate.domain.rules.impl.*;
import br.com.itau.password.validate.domain.services.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class PasswordValidatorServiceConfig {

    @Value("${itau.password.business.rules.password.upper.case.number}")
    private int upperCaseNumber;

    @Value("${itau.password.business.rules.password.lower.case.number}")
    private int lowerCaseNumber;

    @Value("${itau.password.business.rules.password.length}")
    private int length;

    @Value("${itau.password.business.rules.password.special.character}")
    private String specialcharacter;

    @Value("${itau.password.business.rules.password.digit.number}")
    private int digitNumber;

    @Bean
    public PasswordValidatorService passwordValidatorServiceInit() {
        Set<ValidationRule> validationRules = new HashSet<>();
        validationRules.add(new LengthRule(this.length));
        validationRules.add(new UpperCaseRule(this.upperCaseNumber));
        validationRules.add(new LowerCaseRule(this.lowerCaseNumber));
        validationRules.add(new SpecialCharacterRule(this.specialcharacter));
        validationRules.add(new HasNumberRule(this.digitNumber));
        validationRules.add(new BlankCharacterRule());
        validationRules.add(new CharacterRepeaterRule());

        return new PasswordValidatorService(validationRules);
    }
}
