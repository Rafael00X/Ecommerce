package com.ecommerce.inventory.dtos.entity.mappers;

import com.ecommerce.inventory.dtos.entity.CategoryDto;
import com.ecommerce.inventory.models.Category;

public class CategoryDtoMapper {
    public static CategoryDto map(Category category) {
        if (category == null) return null;
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getCategoryType(),
                UnitOfMeasureDtoMapper.map(category.getUnitOfMeasure()), // Assuming UnitOfMeasureDtoMapper exists
                category.getParentCategory() != null ? map(category.getParentCategory()) : null // Recursively map parentCategory
        );
    }
}
