package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.LengthRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LengthRuleTest {

    private static final int LENGTH_NUMBER = 9;

    private final LengthRule rule = new LengthRule(LENGTH_NUMBER);

    private static final String PASSWORD_WITH_LENGTH_ZERO = "";
    private static final String PASSWORD_WITHOUT_LENGTH_ZERO = "Abtp9!foA";
    private static final String CODE = "PWD0001";
    private static final String CODE4 = "PWD0004";

    @Test
    @DisplayName("Should throw PasswordInvalidException when length is zero")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value length is zero")
    void shouldThrowExceptionWhenValueLengthIsZero() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITH_LENGTH_ZERO));
        assertNotNull(exception);
        assertEquals(CODE4, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value length is greater than 1")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITHOUT_LENGTH_ZERO));
    }
}
