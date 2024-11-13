package com.ecommerce.inventory.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VariationValue {
    @Id
    @SequenceGenerator(name = "variationValueSequence", sequenceName = "_sequence_variation_value", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variationValueSequence")
    private Long id;
    private String value;

    @ManyToOne
    @JoinColumn(name = "variation_id", referencedColumnName = "id")
    private Variation variation;
}
