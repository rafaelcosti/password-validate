package br.com.itau.password.validate.ingfrastructure.mappers;

import br.com.itau.password.validate.domain.models.Password;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordRequestDto;

public class PasswordDTOMapper {

    public Password toPassword(PasswordRequestDto passwordRequestDto) {
        return new Password(passwordRequestDto.password());
    }
}
