package com.eduardo.tcgstore.model;

import lombok.*;
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
}
