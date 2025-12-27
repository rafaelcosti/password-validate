package br.com.itau.password.validate.ingfrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record PasswordValidateDTO (
        boolean isValid
        , @JsonInclude(JsonInclude.Include.NON_NULL) ErrorResponse error
) {
}
