package com.ecommerce.inventory.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @SequenceGenerator(name = "categorySequence", sequenceName = "_sequence_category", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    private Long id;
    private String name;
    private String variations;
    private Boolean hasChildCategory;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @ManyToOne
    @JoinColumn(name = "uom_id", referencedColumnName = "id")
    private UnitOfMeasure uom;
}
