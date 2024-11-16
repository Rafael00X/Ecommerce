package com.ecommerce.inventory.services;

import com.ecommerce.inventory.enums.VariationType;
import com.ecommerce.inventory.models.Variation;
import com.ecommerce.inventory.repositories.VariationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VariationService {
    private final VariationRepository variationRepository;

    public Optional<Variation> getVariation(UUID id) {
        return variationRepository.findById(id);
    }

    public Optional<Variation> getVariation(VariationType variationType) {
        return variationRepository.findByName(variationType.name());
    }

    public List<Variation> getVariations() {
        return variationRepository.findAll();
    }

    public Variation addVariation(Variation variation) {
        return variationRepository.save(variation);
    }

    public Variation updateVariation(Variation variation) {
        return variationRepository.save(variation);
    }

    public void deleteVariation(UUID id) {
        variationRepository.deleteById(id);
    }
}
