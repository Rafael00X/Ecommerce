package com.ecommerce.inventory.dtos;

import com.ecommerce.inventory.models.enums.ImageType;

import java.util.UUID;

public record ImageResponseDto(
        UUID id,
        String url,
        ImageType imageType,
        UUID productId
) {
}
