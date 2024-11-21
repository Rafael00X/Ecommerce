package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ProductVariationDto;
import com.ecommerce.inventory.models.ProductVariation;

public class ProductVariationDtoMapper {
    public static ProductVariationDto map(ProductVariation productVariation) {
        return new ProductVariationDto(
                productVariation.getId(),
                productVariation.getProductId(),
                productVariation.getVariationId(),
                productVariation.getVariationValueId(),
                productVariation.getCustomVariationValue(),
                productVariation.getVariation() == null ? null :
                        VariationDtoMapper.map(productVariation.getVariation()),
                productVariation.getVariationValue() == null ? null :
                        VariationValueDtoMapper.map(productVariation.getVariationValue())
        );
    }
}
