package com.ecommerce.inventory.dtos;

import java.util.UUID;

public record UnitOfMeasureDto(
        UUID id,
        String name,
        String code) {
}
