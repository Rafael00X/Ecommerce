package com.ecommerce.inventory.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Variation {
    @Id
    @SequenceGenerator(name = "variationSequence", sequenceName = "_sequence_variation", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variationSequence")
    private Long id;
    private String name;
    private String code;
}
