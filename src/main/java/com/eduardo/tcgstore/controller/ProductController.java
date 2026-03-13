package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.enums.CardGame;
import com.eduardo.tcgstore.enums.ProductCategory;
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
    public String showProducts(Model model){

        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/products/new")
    // para mostrar el formulario vacio
    public String showCreateForm(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("cardGames", CardGame.values());
        model.addAttribute("categories", ProductCategory.values());
        return "products/form";
    }

    @PostMapping("/products")
    public String createProduct(@Valid Product product,     //valida este obj usando las anotaciones del mmodel
                                BindingResult bindingResult,  //guarda los error de validadcion y debe estar junto a @valid
                                Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cardGames", CardGame.values());
            model.addAttribute("categories", ProductCategory.values());
            return "products/form";
        }

        productService.createProduct(product);
        return "redirect:/products";
    }
}
