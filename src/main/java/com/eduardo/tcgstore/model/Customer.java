package com.eduardo.tcgstore.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    @NotBlank(message = "Customer name is required")
    private String name;

    // se valida el formato de correo, este permite null
    @Email(message = "Email format is invalid")
    private String email;
}
