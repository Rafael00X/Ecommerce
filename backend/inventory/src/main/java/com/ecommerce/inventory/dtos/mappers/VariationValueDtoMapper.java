package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.VariationValueDto;
import com.ecommerce.inventory.models.VariationValue;

public class VariationValueDtoMapper {
    public static VariationValueDto map(VariationValue variationValue) {
        return new VariationValueDto(
                variationValue.getId(),
                variationValue.getValue()
        );
    }
}
