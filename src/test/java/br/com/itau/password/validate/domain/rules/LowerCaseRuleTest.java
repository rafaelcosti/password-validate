package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.LowerCaseRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LowerCaseRuleTest {

    private static final int LOWER_CASE_NUMBER = 1;

    private final LowerCaseRule rule = new LowerCaseRule(LOWER_CASE_NUMBER);

    private static final String PASSWORD_WITH_LOWER_CASE = "Abtp9!foa";
    private static final String PASSWORD_WITHOUT_LOWER_CASE = "ABTP9!FOA";
    private static final String CODE = "PWD0001";
    private static final String CODE3 = "PWD0003";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value don`t has lower case character")
    void shouldThrowExceptionWhenValueDontHasLowerCaseCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITHOUT_LOWER_CASE));
        assertNotNull(exception);
        assertEquals(CODE3, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value contain lower case")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITH_LOWER_CASE));
    }
}
