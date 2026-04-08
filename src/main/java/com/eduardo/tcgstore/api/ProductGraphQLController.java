package com.eduardo.tcgstore.api;

import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.getAllProducts();
    }

    @MutationMapping
    public Product createProduct(@Argument String name,
                                 @Argument String cardGame,
                                 @Argument String category,
                                 @Argument Double price,
                                 @Argument Integer stock,
                                 @Argument String status) {

        Product product = new Product();
        product.setName(name);
        product.setCardGame(Product.CardGame.valueOf(cardGame));
        product.setCategory(Product.ProductCategory.valueOf(category));
        product.setPrice(java.math.BigDecimal.valueOf(price));
        product.setStock(stock);
        product.setStatus(Product.ProductStatus.valueOf(status));

        return productService.createProduct(product);
    }
}