package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.ValueNotNullOrRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueNotNullRuleTest {

    private final ValueNotNullOrRule rule = new ValueNotNullOrRule();

    private static final String PASSWORD_NOT_NULL = "AbTp9!foA";
    private static final String PASSWORD_EMPTY = "";
    private static final String CODE = "PWD0001";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(exception.getCode(), CODE);
    }

    @Test
    @DisplayName("Should validate successfully when value is nort null or empty")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_NOT_NULL));
    }
}
