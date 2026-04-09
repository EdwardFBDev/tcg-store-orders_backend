package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.User;
import com.eduardo.tcgstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerRegularUser(User user) {
        if (user == null) {
            throw new BusinessException("User data is required");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BusinessException("Username is required");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BusinessException("Password is required");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BusinessException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.UserRole.REGULAR);
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("User not found"));
    }
}