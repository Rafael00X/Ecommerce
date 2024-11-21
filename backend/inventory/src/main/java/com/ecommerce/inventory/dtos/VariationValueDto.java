package com.ecommerce.inventory.dtos;

import java.util.UUID;

public record VariationValueDto(
    UUID id,
    String value
) {
}
