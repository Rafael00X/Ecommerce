package com.ecommerce.inventory.controllers;

import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public List<Product> getProductsWithQuantityBelowThreshold() {
        return null;
    }
}
