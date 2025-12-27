package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.SpecialCharacterRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpecialCharacterRuleTest {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    private final SpecialCharacterRule rule = new SpecialCharacterRule(SPECIAL_CHARACTERS);

    private static final String CODE = "PWD0001";
    private static final String CODE5 = "PWD0005";

    private static final String PASSWORD_WITH_SPECIAL_CHARACTERS = "Abtp9!foa";
    private static final String PASSWORD_WITHOUT_SPECIAL_CHARACTERS = "abtp9foa";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value don`t has special character")
    void shouldThrowExceptionWhenValueDontHasUSpecialCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITHOUT_SPECIAL_CHARACTERS));
        assertNotNull(exception);
        assertEquals(CODE5, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value contain special character")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITH_SPECIAL_CHARACTERS));
    }
}
