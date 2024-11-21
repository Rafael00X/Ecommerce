package com.ecommerce.inventory.dtos;

import java.util.UUID;

public record ProductVariationDto(
    UUID id,
    UUID productId,
    UUID variationId,
    UUID variationValueId,
    String customVariationValue,
    VariationDto variation,
    VariationValueDto variationValue
) {
}
