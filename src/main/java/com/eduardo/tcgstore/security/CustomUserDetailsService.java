package com.eduardo.tcgstore.security;

import com.eduardo.tcgstore.model.User;
import com.eduardo.tcgstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 *
 * Loads users from the database during authentication
 * and converts them into Spring Security user details.
 *
 * This allows registered users stored in the USERS table
 * to authenticate through the login page.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(dbUser.getUsername())
                .password(dbUser.getPassword())
                .roles(dbUser.getRole().name())
                .disabled(!Boolean.TRUE.equals(dbUser.getEnabled()))
                .build();
    }
}