package com.skillsystem.service;

import com.skillsystem.dto.LoginRequestDTO;
import com.skillsystem.dto.LoginResponseDTO;
import com.skillsystem.dto.RegisterRequestDTO;
import com.skillsystem.entity.User;
import com.skillsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDTO request) {
        boolean userExists = userRepository.findAll().stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(request.getUsername()));

        if (userExists) {
            throw new RuntimeException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), encodedPassword);
        userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<User> userOpt = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(request.getUsername()))
                .findFirst();

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = "fake-jwt-token-for-" + user.getUsername();

        return new LoginResponseDTO(token);
    }
}