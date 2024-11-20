package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ProductResponseDto;
import com.ecommerce.inventory.models.Product;

public class ProductResponseDtoMapper {
    public static ProductResponseDto map(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getProductType(),
                product.getThumbnailImage(),
                product.getChildProducts() == null ? null :
                        product.getChildProducts().stream()
                                .map(ProductResponseDtoMapper::map)
                                .toList(),
                product.getImages() == null ? null :
                        product.getImages().stream()
                                .map(ImageResponseDtoMapper::map)
                                .toList()
        );
    }
}
