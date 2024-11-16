package com.ecommerce.inventory.dtos;

import com.ecommerce.inventory.enums.ProductType;

import java.util.List;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        String description,
        Integer stock,
        Integer stockThreshold,
        Integer price,
        String variant,
        String thumbnailUrl,
        ProductType productType,
        List<ProductResponseDto> childProducts,
        List<ImageResponseDto> images
) {
}
