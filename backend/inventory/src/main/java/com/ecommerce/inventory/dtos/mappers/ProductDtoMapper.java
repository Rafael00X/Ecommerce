package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ProductDto;
import com.ecommerce.inventory.models.Product;

public class ProductDtoMapper {
    public static ProductDto map(Product product) {
        if (product == null) return null;
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getProductType(),
                product.getIncludedFields().contains(Product.Field.THUMBNAIL_IMAGE) ? ImageDtoMapper.map(product.getThumbnailImage()) : null,
//                product.getIncludedFields().contains(Product.Field.CATEGORY) ? CategoryDtoMapper.map(product.getCategory()) : null,
                null,
                product.getIncludedFields().contains(Product.Field.IMAGES) ?
                        product.getImages().stream()
                                .map(ImageDtoMapper::map)
                                .toList() : null,
                product.getIncludedFields().contains(Product.Field.PRODUCT_VARIATIONS) ?
                        product.getProductVariations().stream()
                                .map(ProductVariationDtoMapper::map)
                                .toList() : null,
                product.getIncludedFields().contains(Product.Field.CHILD_PRODUCTS) ?
                        product.getChildProducts().stream()
                                .map(ProductDtoMapper::map)
                                .toList() : null
        );
    }
}
