package com.ecommerce.inventory.models;

import com.ecommerce.inventory.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Integer stock;
    private Integer stockThreshold;
    private Integer price;
    private String variant;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
    private Product parentProduct;

    @Column(name = "parent_product_id", insertable = false, updatable = false)
    private UUID parentProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Transient
    private List<Product> childProducts;
}
