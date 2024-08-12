package ru.bellst.authorization.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellst.authorization.domain.dto.SignUpRequest;
import ru.bellst.authorization.domain.dto.UserProfileDto;
import ru.bellst.authorization.domain.model.User;
import ru.bellst.authorization.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public User registerUser(SignUpRequest registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserProfile(UserProfileDto profileDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setDateOfBirth(profileDto.getDateOfBirth());
        user.setEmail(profileDto.getEmail());
        user.setPhoneNumber(profileDto.getPhoneNumber());

        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
