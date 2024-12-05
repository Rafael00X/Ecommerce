package com.ecommerce.inventory.dtos.entity.mappers;

import com.ecommerce.inventory.dtos.entity.UnitOfMeasureDto;
import com.ecommerce.inventory.models.UnitOfMeasure;

public class UnitOfMeasureDtoMapper {
    public static UnitOfMeasureDto map(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) return null;
        return new UnitOfMeasureDto(
                unitOfMeasure.getId(),
                unitOfMeasure.getName(),
                unitOfMeasure.getCode()
        );
    }
}
