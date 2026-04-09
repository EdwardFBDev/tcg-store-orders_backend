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