package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.HasNumberRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HasNumberTest {

    private static final int DIGIT_NUMBER = 1;

    private final HasNumberRule rule = new HasNumberRule(DIGIT_NUMBER);

    private static final String PASSWORD_WITH_DIGIT = "Abtp9!foa";
    private static final String PASSWORD_WITHOUT_DIGIT = "Abtp!foa";
    private static final String CODE = "PWD0001";
    private static final String CODE8 = "PWD0008";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value don`t has digit character")
    void shouldThrowExceptionWhenValueDontHasDigitCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITHOUT_DIGIT));
        assertNotNull(exception);
        assertEquals(CODE8, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value contain digit")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITH_DIGIT));
    }
}
