package com.ecommerce.inventory.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UnitOfMeasure {
    @Id
    @SequenceGenerator(name = "unitOfMeasureSequence", sequenceName = "_sequence_unit_of_measure", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unitOfMeasureSequence")
    private Long id;
    private String name;
    private String code;
}
