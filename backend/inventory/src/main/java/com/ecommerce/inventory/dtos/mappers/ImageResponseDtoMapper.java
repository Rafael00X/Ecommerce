package com.ecommerce.inventory.dtos.mappers;

import com.ecommerce.inventory.dtos.ImageResponseDto;
import com.ecommerce.inventory.models.Image;

public class ImageResponseDtoMapper {
    public static ImageResponseDto map(Image image) {
        return new ImageResponseDto(
                image.getId(),
                image.getUrl(),
                image.getImageType(),
                image.getProductId()
        );
    }
}
