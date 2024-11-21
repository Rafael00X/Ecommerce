package com.ecommerce.inventory.models;

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
@Check(constraints = "NOT (variation_value_id IS NOT NULL AND custom_variation_value IS NOT NULL)")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "variation_id", "variation_value_id", "custom_variation_value"}))
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "variation_id", nullable = false)
    private UUID variationId;

    @Column(name = "variation_value_id")
    private UUID variationValueId;

    @Column(name = "custom_variation_value")
    private String customVariationValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id", insertable = false, updatable = false)
    private Variation variation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_value_id", insertable = false, updatable = false)
    private VariationValue variationValue;
}
