package br.com.itau.password.validate.infrastructure.api;

import br.com.itau.password.validate.application.usecases.validate.PasswordValidateInteractorImpl;
import br.com.itau.password.validate.domain.models.Password;
import br.com.itau.password.validate.ingfrastructure.api.PasswordController;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordRequestDto;
import br.com.itau.password.validate.ingfrastructure.mappers.PasswordDTOMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PasswordController.class)
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PasswordValidateInteractorImpl interactor;

    @MockitoBean
    private PasswordDTOMapper mapper;

    private static final String PASSWORD = "password";
    private static final String URI = "/itau/password/validate";

    private final PasswordRequestDto requestDto = new PasswordRequestDto(PASSWORD);
    private final Password passwordDomain = new Password(PASSWORD);

    @Test
    @DisplayName("Should return 200 OK with isValid=true when password is valid")
    void shouldReturnOkWhenPasswordIsValid() throws Exception {
        when(mapper.toPassword(any(PasswordRequestDto.class))).thenReturn(passwordDomain);
        when(interactor.execute(passwordDomain)).thenReturn(true);

        String jsonBody = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isValid").value(true))
                .andExpect(jsonPath("$.errors").doesNotExist());
    }
}
