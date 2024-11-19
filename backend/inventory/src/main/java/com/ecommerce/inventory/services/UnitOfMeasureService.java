package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.enums.UnitOfMeasureType;
import com.ecommerce.inventory.models.UnitOfMeasure;
import com.ecommerce.inventory.repositories.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Optional<UnitOfMeasure> getUnitOfMeasure(UUID id) {
        return unitOfMeasureRepository.findById(id);
    }

    public Optional<UnitOfMeasure> getUnitOfMeasure(UnitOfMeasureType uomType) {
        return unitOfMeasureRepository.findByName(uomType.name());
    }

    public List<UnitOfMeasure> getUnitOfMeasures() {
        return unitOfMeasureRepository.findAll();
    }

    public UnitOfMeasure addUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    public List<UnitOfMeasure> addUnitOfMeasures(List<UnitOfMeasure> unitOfMeasures) {
        return unitOfMeasureRepository.saveAll(unitOfMeasures);
    }

    public UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    public void deleteUnitOfMeasure(UUID id) {
        unitOfMeasureRepository.deleteById(id);
    }
}
