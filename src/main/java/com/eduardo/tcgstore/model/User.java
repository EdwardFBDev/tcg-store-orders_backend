package com.eduardo.tcgstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("USERS")
/**
 * Represents an authenticated user of the system.
 *
 * Users are responsible for authentication and authorization.
 * They can have different roles such as ADMIN or REGULAR,
 * which determine access to protected resources.
 *
 * This entity is separate from Customer to distinguish
 * authentication from business data.
 */
public class User {

    @Id
    private Long id;

    private String username;
    private String password;
    private UserRole role;
    private Boolean enabled;

    public enum UserRole {
        ADMIN,
        REGULAR
    }
}