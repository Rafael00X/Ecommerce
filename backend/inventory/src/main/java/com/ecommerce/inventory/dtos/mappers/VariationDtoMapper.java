package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.VariationDto;
import com.ecommerce.inventory.models.Variation;

public class VariationDtoMapper {
    public static VariationDto map(Variation variation) {
        return new VariationDto(
                variation.getId(),
                variation.getName(),
                variation.getCode()
        );
    }
}
