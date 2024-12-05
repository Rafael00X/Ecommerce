package com.ecommerce.inventory.dtos.entity;

import java.util.UUID;

public record VariationDto(
    UUID id,
    String name,
    String code
) {
}
