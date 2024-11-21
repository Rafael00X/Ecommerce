package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ProductDto;
import com.ecommerce.inventory.models.Product;

public class ProductDtoMapper {
    public static ProductDto map(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getProductType(),
                product.getThumbnailImage(),
                product.getProductVariations() == null ? null :
                        product.getProductVariations().stream()
                                .map(ProductVariationDtoMapper::map)
                                .toList(),
                product.getChildProducts() == null ? null :
                        product.getChildProducts().stream()
                                .map(ProductDtoMapper::map)
                                .toList(),
                product.getImages() == null ? null :
                        product.getImages().stream()
                                .map(ImageDtoMapper::map)
                                .toList()
        );
    }
}
