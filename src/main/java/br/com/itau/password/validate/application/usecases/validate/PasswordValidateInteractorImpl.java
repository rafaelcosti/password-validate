package br.com.itau.password.validate.application.usecases.validate;

import br.com.itau.password.validate.domain.models.Password;
import br.com.itau.password.validate.domain.services.PasswordValidatorService;

public class PasswordValidateInteractorImpl implements PasswordValidateInteractor {

    public PasswordValidateInteractorImpl(PasswordValidatorService passwordValidatorService) {
        this.passwordValidatorService = passwordValidatorService;
    }

    private final PasswordValidatorService passwordValidatorService;

    @Override
    public boolean execute(Password passwordModel) {
        this.passwordValidatorService.validate(passwordModel.password());
        return true;
    }
}
