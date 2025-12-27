package br.com.itau.password.validate.infrastructure.handlers;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordValidateDTO;
import br.com.itau.password.validate.ingfrastructure.handlers.ExceptionGlobalHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExceptionGlobalHandlerTest {

    private final ExceptionGlobalHandler handler = new ExceptionGlobalHandler();

    @Mock
    private PasswordInvalidException exception;

    private static final String EXPECTED_CODE = "PWD-001";
    private static final String EXPECTED_MESSAGE = "Validation error message";

    @Test
    @DisplayName("Should handle PasswordInvalidException and return DTO with correct error details")
    void shouldHandlePasswordInvalidException() {
        when(exception.getCode()).thenReturn(EXPECTED_CODE);
        when(exception.getMessage()).thenReturn(EXPECTED_MESSAGE);

        PasswordValidateDTO result = handler.handlePasswordBusinessException(exception);

        assertNotNull(result);
        assertNotNull(result.error());
        assertFalse(result.isValid());
        assertEquals(EXPECTED_CODE, result.error().code());
        assertEquals(EXPECTED_MESSAGE, result.error().message());
    }
}
