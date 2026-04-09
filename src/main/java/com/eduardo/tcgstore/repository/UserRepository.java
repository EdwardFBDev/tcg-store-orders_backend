package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/**
 * Repository responsible for user authentication data.
 *
 * Used by the security layer to retrieve users by username
 * and validate credentials during login.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
