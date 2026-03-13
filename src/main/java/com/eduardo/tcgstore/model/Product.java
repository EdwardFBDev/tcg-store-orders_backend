package com.eduardo.tcgstore.model;

import com.eduardo.tcgstore.enums.CardGame;
import com.eduardo.tcgstore.enums.ProductCategory;
import lombok.*;

//validation
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    @NotBlank(message = "Product name is required") //no nulls
    private String name;

    @NotNull(message = "Card game is required") // obliga a que contenga un valor
    private CardGame cardGame;

    @NotNull(message = "Category is required")
    private ProductCategory category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be 0 or greater") //valida montos decimales
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be 0 or greater") // valida enteros como stock...
    private int stock;

    private boolean active;
}
