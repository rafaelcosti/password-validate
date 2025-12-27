package br.com.itau.password.validate.ingfrastructure.handlers;

import br.com.itau.password.validate.domain.exceptions.PasswordInvalidException;
import br.com.itau.password.validate.ingfrastructure.dto.ErrorResponse;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordValidateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(PasswordInvalidException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public PasswordValidateDTO handlePasswordBusinessException(PasswordInvalidException exception) {
        log.warn("The application has a business warning.", exception);
        ErrorResponse error = new ErrorResponse(exception.getCode(), exception.getMessage());
        return new PasswordValidateDTO(false, error);
    }
}
