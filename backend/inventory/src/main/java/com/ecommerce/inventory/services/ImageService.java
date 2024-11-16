package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.Image;
import com.ecommerce.inventory.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> getImagesByProductId(UUID productId) {
        return imageRepository.findByProductId(productId);
    }

    public List<Image> addImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }

}
