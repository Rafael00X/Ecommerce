package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.CategoryVariation;
import com.ecommerce.inventory.models.keys.CategoryVariationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryVariationRepository extends JpaRepository<CategoryVariation, CategoryVariationId> {
}
