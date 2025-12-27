package br.com.itau.password.validate.infrastructure.mappers;

import br.com.itau.password.validate.domain.models.Password;
import br.com.itau.password.validate.ingfrastructure.dto.PasswordRequestDto;
import br.com.itau.password.validate.ingfrastructure.mappers.PasswordDTOMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordDTOMapperTest {

    private final PasswordDTOMapper mapper = new PasswordDTOMapper();

    private static final String EXPECTED_PASSWORD = "password123";

    private static final String PASSWORD_NULL = null;

    @Test
    @DisplayName("Given a valid DTO, when mapping to entity, then should return entity with correct password")
    void shouldMapDtoToEntitySuccessfully() {

        PasswordRequestDto passwordRequestDto = new PasswordRequestDto(EXPECTED_PASSWORD);
        Password result = mapper.toPassword(passwordRequestDto);

        assertNotNull(result);
        assertEquals(EXPECTED_PASSWORD, result.password());
    }

    @Test
    @DisplayName("Given a valid DTO but the password value is null")
    void shouldValueMapDtoToEntityIsNull() {
        PasswordRequestDto passwordRequestDto = new PasswordRequestDto(PASSWORD_NULL);
        Password result = mapper.toPassword(passwordRequestDto);

        assertNotNull(result);
        assertNull(result.password());
    }
}
