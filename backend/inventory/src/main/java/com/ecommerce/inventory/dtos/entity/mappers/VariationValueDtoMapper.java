package com.ecommerce.inventory.dtos.entity.mappers;

import com.ecommerce.inventory.dtos.entity.VariationValueDto;
import com.ecommerce.inventory.models.VariationValue;

public class VariationValueDtoMapper {
    public static VariationValueDto map(VariationValue variationValue) {
        if (variationValue == null) return null;
        return new VariationValueDto(
                variationValue.getId(),
                variationValue.getValue()
        );
    }
}
