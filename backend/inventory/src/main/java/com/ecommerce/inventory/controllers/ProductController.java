package com.ecommerce.inventory.controllers;


import com.ecommerce.inventory.dtos.AddParentProductRequestDto;
import com.ecommerce.inventory.dtos.AddProductRequestDto;
import com.ecommerce.inventory.dtos.entity.ProductDto;
import com.ecommerce.inventory.dtos.entity.mappers.ProductDtoMapper;
import com.ecommerce.inventory.models.Category;
import com.ecommerce.inventory.models.Image;
import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.models.ProductVariation;
import com.ecommerce.inventory.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(ProductDtoMapper::map).toList();
    }

    @PostMapping("/")
    public ProductDto addProduct(@Valid @RequestBody AddProductRequestDto dto) {
        Product product = Product.builder()
                .name(dto.name())
                .description(dto.description())
                .stock(dto.stock())
                .price(dto.price())
                .category(Category.builder().id(dto.categoryId()).build())
                .images(dto.imageUrls().stream().map(url -> Image.builder().url(url).build()).toList())
                .build();

        product = productService.addProduct(product);
        return ProductDtoMapper.map(product);
    }

    @PostMapping("/add-parent-product")
    public Product addParentProduct(@Valid @RequestBody AddParentProductRequestDto dto) {
        Product parentProduct = Product.builder()
                .name(dto.name())
                .category(Category.builder().id(dto.categoryId()).build())
                .build();

        List<UUID> parentProductVariationIds = dto.variationIds();

        List<UUID> childProductIds = new ArrayList<>();
        List<ProductVariation> childProductVariations = new ArrayList<>();

        dto.childProductIdToVariant().forEach((childProductId, variantList) -> {
            childProductIds.add(childProductId);
            variantList.forEach(variant -> {
                childProductVariations.add(ProductVariation.builder()
                        .productId(childProductId)
                        .variationId(variant.variantId())
                        .variationValueId(variant.variantValueId())
                        .customVariationValue(variant.customVariantValue())
                        .build());
            });
        });


        return productService.addParentProduct(parentProduct, parentProductVariationIds, childProductIds, childProductVariations);
    }

}
