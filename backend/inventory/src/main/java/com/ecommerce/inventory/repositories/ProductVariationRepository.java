package com.ecommerce.inventory.repositories;

import com.ecommerce.inventory.models.ProductVariation;
import com.ecommerce.inventory.models.keys.ProductVariationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, ProductVariationId> {
}
