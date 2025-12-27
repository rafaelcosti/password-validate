package br.com.itau.password.validate.ingfrastructure.configs;

import br.com.itau.password.validate.application.usecases.validate.PasswordValidateInteractorImpl;
import br.com.itau.password.validate.domain.services.PasswordValidatorService;
import br.com.itau.password.validate.ingfrastructure.mappers.PasswordDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordValidateInteractorImpl passwordValidateInteractorInit(PasswordValidatorService passwordValidatorServiceInit) {
        return new PasswordValidateInteractorImpl(passwordValidatorServiceInit);
    }

    @Bean
    public PasswordDTOMapper passwordDTOMapperInit() {
        return new PasswordDTOMapper();
    }

}
