package ru.bellst.authorization.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {

    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;

    @Schema(description = "Тип токена", example = "Bearer")
    private final String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
