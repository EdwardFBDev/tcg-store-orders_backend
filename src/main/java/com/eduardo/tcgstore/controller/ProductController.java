package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// esto hace que spring maneje rutas web y retornara vistas html
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("cardGames", Product.CardGame.values());
        model.addAttribute("categories", Product.ProductCategory.values());
        model.addAttribute("statuses", Product.ProductStatus.values());
        return "products/form";
    }

    @PostMapping("/products")
    public String createProduct(
            @Valid Product product,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cardGames", Product.CardGame.values());
            model.addAttribute("categories", Product.ProductCategory.values());
            model.addAttribute("statuses", Product.ProductStatus.values());
            return "products/form";
        }

        if (product.getStatus() == null) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        productService.createProduct(product);
        return "redirect:/products";
    }
}
