package com.ecommerce.inventory.dtos;

import com.ecommerce.inventory.models.Image;
import com.ecommerce.inventory.models.enums.ProductType;

import java.util.List;
import java.util.UUID;

//public record ProductResponseDto(
//        UUID id,
//        String name,
//        String description,
//        Integer stock,
//        Integer stockThreshold,
//        Integer price,
//        String variant,
//        String thumbnailUrl,
//        ProductType productType,
//        List<ProductResponseDto> childProducts,
//        List<ImageResponseDto> images
//) {
//}

public record ProductResponseDto(
        UUID id,
        String name,
        String description,
        Integer stock,
        Integer price,
        ProductType productType,
        Image thumbnailImage, // Derived from the thumbnail image
        List<ProductResponseDto> childProducts, // Maps `childProducts` from `Product`
        List<ImageResponseDto> images // Maps `images` from `Product`
) {
}