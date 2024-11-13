package com.ecommerce.inventory.services;

import com.ecommerce.inventory.repositories.VariationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VariationService {
    private final VariationRepository variationRepository;
}
