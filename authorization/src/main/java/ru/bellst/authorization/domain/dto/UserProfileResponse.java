package ru.bellst.authorization.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.bellst.authorization.domain.model.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Schema(description = "Ответ c данными пользователя")
public class UserProfileResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;

    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
