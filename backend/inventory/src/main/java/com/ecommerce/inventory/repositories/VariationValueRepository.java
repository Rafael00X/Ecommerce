package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.VariationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationValueRepository extends JpaRepository<VariationValue, Long> {
}
