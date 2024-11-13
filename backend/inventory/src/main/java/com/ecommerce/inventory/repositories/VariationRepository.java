package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {
}
