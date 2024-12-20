package com.ecommerce.inventory.models;

import com.ecommerce.inventory.models.keys.CategoryVariationId;
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
@IdClass(CategoryVariationId.class)
public class CategoryVariation {
    @Id
    @Column(name = "category_id")
    private UUID categoryId;

    @Id
    @Column(name = "variation_id")
    private UUID variationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id", insertable = false, updatable = false)
    private Variation variation;
}
