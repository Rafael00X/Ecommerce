package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, UUID> {
    Optional<UnitOfMeasure> findByName(String name);
}
