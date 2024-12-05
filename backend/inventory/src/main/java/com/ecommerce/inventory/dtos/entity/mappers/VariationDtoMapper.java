package com.ecommerce.inventory.dtos.entity.mappers;


import com.ecommerce.inventory.dtos.entity.VariationDto;
import com.ecommerce.inventory.models.Variation;

public class VariationDtoMapper {
    public static VariationDto map(Variation variation) {
        if (variation == null) return null;
        return new VariationDto(
                variation.getId(),
                variation.getName(),
                variation.getCode()
        );
    }
}
