package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.VariationValue;
import com.ecommerce.inventory.repositories.VariationValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VariationValueService {
    private final VariationValueRepository variationValueRepository;

    public List<VariationValue> getVariationValues(UUID variationId) {
        return variationValueRepository.findByVariationId(variationId);
    }

    public List<VariationValue> getVariationValues() {
        return variationValueRepository.findAll();
    }

    public VariationValue addVariationValue(VariationValue variationValue) {
        return variationValueRepository.save(variationValue);
    }

    public VariationValue updateVariationValue(VariationValue variationValue) {
        return variationValueRepository.save(variationValue);
    }

    public void deleteVariationValue(UUID id) {
        variationValueRepository.deleteById(id);
    }
}
