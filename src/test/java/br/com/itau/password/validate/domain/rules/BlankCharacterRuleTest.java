package br.com.itau.password.validate.domain.rules;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.impl.BlankCharacterRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlankCharacterRuleTest {

    private final BlankCharacterRule rule = new BlankCharacterRule();

    private static final String PASSWORD_WITH_BLANK = "Abtp9! foA";
    private static final String PASSWORD_WITHOUT_BLANK = "Abtp9!fow";
    private static final String CODE6 = "PWD0006";

    @Test
    @DisplayName("Should throw PasswordInvalidException when value there are blank character")
    void shouldThrowExceptionWhenValueThereAreBlankCharacter() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> rule.validate(PASSWORD_WITH_BLANK));
        assertNotNull(exception);
        assertEquals(CODE6, exception.getCode());
    }

    @Test
    @DisplayName("Should validate successfully when value doesn’t blank character")
    void shouldValidateSuccessfullyWhenValueIsPresent() {
        assertDoesNotThrow(() -> rule.validate(PASSWORD_WITHOUT_BLANK));
    }
}
