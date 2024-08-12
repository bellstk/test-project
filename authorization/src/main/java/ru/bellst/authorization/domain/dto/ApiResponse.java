package ru.bellst.authorization.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Общий ответ API")
public class ApiResponse {

    @Schema(description = "Состояние", example = "true")
    private boolean success;

    @Schema(description = "Ответ сервера", example = "User registered successfully!")
    private String message;
}
