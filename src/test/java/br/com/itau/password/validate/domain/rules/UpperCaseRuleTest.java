package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.UpperCaseRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpperCaseRuleTest {

    private static final int UPPER_CASE_NUMBER = 1;

    private final UpperCaseRule rule = new UpperCaseRule(UPPER_CASE_NUMBER);

    private static final String PASSWORD_WITH_UPPER_CASE = "Abtp9!foa";
    private static final String PASSWORD_WITHOUT_UPPER_CASE = "abtp9!foa";
    private static final String CODE = "PWD0001";
    private static final String CODE2 = "PWD0002";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value don`t has upper case character")
    void shouldThrowExceptionWhenValueDontHasUpperCaseCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITHOUT_UPPER_CASE));
        assertNotNull(exception);
        assertEquals(CODE2, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value contain upper case")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITH_UPPER_CASE));
    }
}
