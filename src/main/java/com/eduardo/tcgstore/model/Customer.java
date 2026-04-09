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
@Table("CUSTOMER")
/**
 * Represents a customer in the system.
 *
 * Customers are linked to orders and represent the buyer identity.
 * In this project, customers are created automatically based on
 * authenticated users during checkout.
 */
public class Customer {

    @Id
    private Long id;

    private String name;
    private String email;
}