package com.ecommerce.inventory.dtos;

import java.util.UUID;

public record CategoryVariationDto(
        UUID categoryId,
        UUID variationId,
        CategoryDto category,
        VariationDto variation
) {
}
