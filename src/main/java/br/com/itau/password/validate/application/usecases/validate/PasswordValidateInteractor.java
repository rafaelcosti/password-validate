package br.com.itau.password.validate.application.usecases.validate;

import br.com.itau.password.validate.domain.models.Password;

public interface PasswordValidateInteractor {

    boolean execute(Password password);
}
