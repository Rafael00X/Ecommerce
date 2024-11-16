package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VariationRepository extends JpaRepository<Variation, UUID> {
    Optional<Variation> findByName(String name);
}
