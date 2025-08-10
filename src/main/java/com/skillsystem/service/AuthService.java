package com.skillsystem.service;

import com.skillsystem.dto.LoginRequestDTO;
import com.skillsystem.dto.LoginResponseDTO;
import com.skillsystem.dto.RegisterRequestDTO;
import com.skillsystem.entity.User;
import com.skillsystem.exception.NotFoundException;
import com.skillsystem.repository.UserRepository;
import com.skillsystem.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequestDTO request) {
        boolean exists = userRepository.findAll().stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(request.getUsername()));
        if (exists) throw new RuntimeException("Username already exists");

        String hash = passwordEncoder.encode(request.getPassword());
        userRepository.save(new User(request.getUsername(), hash));
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        String username = request.getUsername() == null ? "" : request.getUsername().trim();

        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new NotFoundException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponseDTO(token);
    }
}