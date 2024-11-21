package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.ProductVariation;
import com.ecommerce.inventory.repositories.ProductVariationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductVariationService {
    private final ProductVariationRepository productVariationRepository;

    public List<ProductVariation> addProductVariations(List<ProductVariation> productVariations) {
        return productVariationRepository.saveAll(productVariations);
    }
}
