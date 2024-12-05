package com.ecommerce.inventory.dtos.entity;

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
