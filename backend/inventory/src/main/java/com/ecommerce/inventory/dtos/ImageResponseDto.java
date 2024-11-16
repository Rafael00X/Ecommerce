package com.ecommerce.inventory.dtos;

import java.util.UUID;

public record ImageResponseDto(
        UUID id,
        String url
) {
}
