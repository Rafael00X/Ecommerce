package com.ecommerce.inventory.models;

import com.ecommerce.inventory.models.keys.ProductVariationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@IdClass(ProductVariationId.class)
@Check(constraints = "NOT (variation_value_id IS NOT NULL AND custom_variation_value IS NOT NULL)")
public class ProductVariation {
    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Id
    @Column(name = "variation_id")
    private UUID variationId;

    @Id
    @Column(name = "variation_value_id")
    private UUID variationValueId;

    @Id
    @Column(name = "custom_variation_value")
    private String customVariationValue;

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
