package com.ecommerce.inventory.controllers;

import com.ecommerce.inventory.dtos.AddParentProductRequestDto;
import com.ecommerce.inventory.dtos.AddProductRequestDto;
import com.ecommerce.inventory.dtos.ProductResponseDto;
import com.ecommerce.inventory.dtos.mappers.ProductResponseDtoMapper;
import com.ecommerce.inventory.models.Category;
import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(ProductResponseDtoMapper::map).toList();
    }

    @PostMapping("/")
    public ProductResponseDto addProduct(@Valid @RequestBody AddProductRequestDto dto) {
        Product product = Product.builder()
                .name(dto.name())
                .description(dto.description())
                .stock(dto.stock())
                .stockThreshold(dto.stockThreshold())
                .price(dto.price())
                .category(Category.builder().id(dto.categoryId()).build())
                .build();

        product = productService.addProduct(product);
        return ProductResponseDtoMapper.map(product);
    }

    @PostMapping("/add-parent-product")
    public Product addParentProduct(@Valid @RequestBody AddParentProductRequestDto dto) {
        Product product = Product.builder()
                .name(dto.name())
                .variant(dto.variant())
                .category(Category.builder().id(dto.categoryId()).build())
                .build();

        return productService.addParentProduct(product, dto.childProductIdToVariant());
    }

}
