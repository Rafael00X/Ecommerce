package com.ecommerce.inventory.dtos.entity.mappers;

import com.ecommerce.inventory.dtos.entity.CategoryVariationDto;
import com.ecommerce.inventory.models.CategoryVariation;

public class CategoryVariationDtoMapper {
    public static CategoryVariationDto map(CategoryVariation categoryVariation) {
        if (categoryVariation == null) return null;
        return new CategoryVariationDto(
                categoryVariation.getCategoryId(),
                categoryVariation.getVariationId(),
                CategoryDtoMapper.map(categoryVariation.getCategory()),
                VariationDtoMapper.map(categoryVariation.getVariation())
        );
    }
}
