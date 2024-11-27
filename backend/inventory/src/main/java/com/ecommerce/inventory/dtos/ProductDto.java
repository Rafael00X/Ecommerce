package com.ecommerce.inventory.dtos;

import com.ecommerce.inventory.models.enums.ProductType;

import java.util.List;
import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        String description,
        Integer stock,
        Integer price,
        ProductType productType,
        ImageDto thumbnailImage,
        CategoryDto category,
        List<ImageDto> images,
        List<ProductVariationDto> productVariations,
        List<ProductDto> childProducts
) {
}