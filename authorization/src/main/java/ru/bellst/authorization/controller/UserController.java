package ru.bellst.authorization.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ru.bellst.authorization.domain.dto.SignInRequest;
import ru.bellst.authorization.domain.dto.UserProfileDto;
import ru.bellst.authorization.domain.dto.SignUpRequest;
import ru.bellst.authorization.domain.model.User;
import ru.bellst.authorization.domain.dto.ApiResponse;
import ru.bellst.authorization.domain.dto.JwtAuthenticationResponse;
import ru.bellst.authorization.domain.dto.UserProfileResponse;
import ru.bellst.authorization.security.JwtTokenProvider;
import ru.bellst.authorization.service.UserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "User Controller", description = "API для управления пользователями")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя", description = "Создает нового пользователя в системе")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest registrationDto) {
        if (userService.existsByUsername(registrationDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(false, "Username is already taken!"));
        }

        User user = userService.registerUser(registrationDto);

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true, "User registered successfully!"));
    }

    @PostMapping("/login")
    @Operation(summary = "Аутентификация пользователя", description = "Аутентифицирует пользователя и возвращает JWT токен")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/profile")
    @Operation(summary = "Обновление профиля пользователя", description = "Обновляет данные профиля авторизова`нного пользователя")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtTokenProvider.getUsername(jwt);

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid or expired JWT token"));
        }

        User user = userService.getUserByUsername(username);

        return ResponseEntity.ok(new UserProfileResponse(user));
    }

    @PutMapping("/profile")
    @Operation(summary = "Получение профиля пользователя", description = "Возвращает данные профиля авторизованного пользователя")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserProfileDto profileDto, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtTokenProvider.getUsername(jwt);

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid or expired JWT token"));
        }

        User updatedUser = userService.updateUserProfile(profileDto, username);

        return ResponseEntity.ok(new UserProfileResponse(updatedUser));
    }
}
