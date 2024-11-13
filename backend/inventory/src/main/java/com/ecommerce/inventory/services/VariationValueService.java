package com.ecommerce.inventory.services;

import com.ecommerce.inventory.repositories.VariationValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VariationValueService {
    private final VariationValueRepository variationValueRepository;
}
