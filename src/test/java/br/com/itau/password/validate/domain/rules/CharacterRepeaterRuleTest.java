package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.CharacterRepeaterRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CharacterRepeaterRuleTest {

    private final CharacterRepeaterRule rule = new CharacterRepeaterRule();

    private static final String PASSWORD_WITH_REPEATER = "Abtp9!foA";
    private static final String PASSWORD_WITHOUT_REPEATER = "Abtp9!fow";
    private static final String CODE = "PWD0001";
    private static final String CODE7 = "PWD0007";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(null));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }

    @Test
    @DisplayName("Should throw PasswordInvalidException when value there are repeat character")
    void shouldThrowExceptionWhenValueThereAreRepeatCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITH_REPEATER));
        assertNotNull(exception);
        assertEquals(CODE7, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value don`t repeat character")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITHOUT_REPEATER));
    }
}
