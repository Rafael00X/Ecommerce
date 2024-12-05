package com.ecommerce.inventory.dtos.entity;

import com.ecommerce.inventory.models.enums.CategoryType;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        CategoryType categoryType,
        UnitOfMeasureDto unitOfMeasure,
        CategoryDto parentCategory
) {
}
