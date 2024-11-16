package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.VariationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VariationValueRepository extends JpaRepository<VariationValue, UUID> {
    List<VariationValue> findByVariationId(UUID variationId);
}
