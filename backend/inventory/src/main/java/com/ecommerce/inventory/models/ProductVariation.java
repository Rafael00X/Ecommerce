package com.ecommerce.inventory.models;

import com.ecommerce.inventory.models.keys.ProductVariationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@IdClass(ProductVariationId.class)
public class ProductVariation {
    @Id
    private UUID productId;

    @Id
    private UUID variationId;

    @Id
    private UUID variationValueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false, nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id", insertable = false, updatable = false, nullable = false)
    private Variation variation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_value_id", insertable = false, updatable = false)
    private VariationValue variationValue;
}
