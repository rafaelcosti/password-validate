package br.com.itau.password.validate.ingfrastructure.api;

import br.com.itau.password.validate.ingfrastructure.dto.PasswordRequestDto;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordValidateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/itau/password")
@Tag(name = "Password", description = "API para gerenciamento de senhas")
public interface PasswordResource {

    @Operation(summary = "Validador de senhas", description = "Retorna retorna se uma senha é válida ou não conforme os requisitos mínimos abaixo\n" +
            "- Nove ou mais caracteres\n" +
            "- Ao menos 1 dígito\n" +
            "- Ao menos 1 letra minúscula\n" +
            "- Ao menos 1 letra maiúscula\n" +
            "- Náo permitido espeços em branco\n" +
            "- Ao menos 1 caractere especial\n" +
            "    - Considere como especial os seguintes caracteres: !@#$%^&*()-+\n" +
            "- Não possuir caracteres repetidos dentro do conjunto")
    @ApiResponse(responseCode = "200", description = "Senha validad com psucesso")
    @ApiResponse(responseCode = "412", description = "Senha não atende aos requisitos mínimos")
    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PasswordValidateDTO> validate(@RequestBody  PasswordRequestDto request);
}
