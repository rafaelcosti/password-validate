package br.com.itau.password.validate.ingfrastructure.api;

import br.com.itau.password.validate.application.usecases.validate.PasswordValidateInteractorImpl;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordRequestDto;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordValidateDTO;
import br.com.itau.password.validate.ingfrastructure.mappers.PasswordDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordController implements PasswordResource {

    private final PasswordValidateInteractorImpl passwordValidateInteractor;

    private final PasswordDTOMapper passwordDTOMapper;

    @Override
    public ResponseEntity<PasswordValidateDTO> validate(@RequestBody PasswordRequestDto request) {
        boolean isValid = this.passwordValidateInteractor.execute(passwordDTOMapper.toPassword(request));
        return ResponseEntity.ok(new PasswordValidateDTO(isValid, null));
    }
}
