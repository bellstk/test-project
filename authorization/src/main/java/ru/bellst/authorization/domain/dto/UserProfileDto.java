package ru.bellst.authorization.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "DTO данных пользователя")
public class UserProfileDto {

    @Schema(description = "Имя пользователя", example = "Jon")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Dow")
    private String lastName;

    @Schema(description = "Дата рождения пользователя", example = "1999-11-21")
    private LocalDate dateOfBirth;

    @Email
    @Schema(description = "Email пользователя", example = "jon.dow@gmail.com")
    private String email;

    @Schema(description = "Мобильный телефон пользователя", example = "89195575288")
    @Size(min = 11, max = 13, message = "Длина номера телефона должна быть от 11 до 13 символов")
    private String phoneNumber;
}
