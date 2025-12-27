package br.com.itau.password.validate.domain.services;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.rules.ValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorServiceTest {

    @Mock
    private ValidationRule rule1;

    @Mock
    private ValidationRule rule2;

    private static final String PASSWORD = "password";
    private static final String CODE = "ERR001";
    private static final String MESSAGE_ERROR = "Validation error";
;
    private PasswordValidatorService service;

    @BeforeEach
    public void init(){
        service = new PasswordValidatorService(Set.of(rule1, rule2));
    }

    @Test
    @DisplayName("Should iterate and delegate validation to all provided rules")
    void shouldDelegateValidationToAllRules() {
        service.validate(PASSWORD);
        verify(rule1).validate(PASSWORD);
        verify(rule2).validate(PASSWORD);
    }

    @Test
    @DisplayName("Should propagate exception when a rule fails")
    void shouldPropagateExceptionWhenRuleFails() {
        PasswordInvalidException expectedException = new PasswordInvalidException(MESSAGE_ERROR, CODE);
        doThrow(expectedException).when(rule1).validate(PASSWORD);

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> service.validate(PASSWORD));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }
}
