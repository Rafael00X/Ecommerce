package com.ecommerce.inventory.dtos.entity;

import java.util.UUID;

public record VariationValueDto(
    UUID id,
    String value
) {
}
