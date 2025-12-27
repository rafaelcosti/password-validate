package br.com.itau.password.validate.application.usecases.validate;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.domain.models.Password;
import br.com.itau.password.validate.domain.services.PasswordValidatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordValidateInteractorImplTest {
    @Mock
    private PasswordValidatorService passwordValidatorService;

    @Mock
    private Password passwordModel;

    @InjectMocks
    private PasswordValidateInteractorImpl interactor;

    private static final String PASSWORD = "password";
    private static final String CODE = "ERR001";
    private static final String MESSAGE_ERROR = "Validation error";
    private static final PasswordInvalidException EXPECTED_EXCEPTION = new PasswordInvalidException(MESSAGE_ERROR, CODE);

    @Test
    @DisplayName("Should validate password successfully and return true")
    void shouldValidatePasswordSuccessfully() {
        when(passwordModel.password()).thenReturn(PASSWORD);

        boolean result = interactor.execute(passwordModel);

        assertTrue(result);
        verify(passwordValidatorService).validate(PASSWORD);
    }


    @Test
    @DisplayName("Should propagate exception when validation service fails")
    void shouldPropagateExceptionWhenValidationFails() {
        when(passwordModel.password()).thenReturn(PASSWORD);
        doThrow(EXPECTED_EXCEPTION).when(passwordValidatorService).validate(PASSWORD);

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class
                , () -> interactor.execute(passwordModel));
        assertNotNull(exception);
        assertEquals(CODE, exception.getCode());
    }
}
