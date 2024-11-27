package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ImageDto;
import com.ecommerce.inventory.models.Image;

public class ImageDtoMapper {
    public static ImageDto map(Image image) {
        if (image == null) return null;
        return new ImageDto(
                image.getId(),
                image.getUrl(),
                image.getImageType(),
                image.getProductId()
        );
    }
}
