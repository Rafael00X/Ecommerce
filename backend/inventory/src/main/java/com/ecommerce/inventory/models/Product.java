package com.ecommerce.inventory.models;

import com.ecommerce.inventory.models.enums.ProductType;
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

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer stock;
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thumbnail_image_id", referencedColumnName = "id")
    private Image thumbnailImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    //    @Column(name = "product_variation_id", insertable = false, updatable = false)
    //    private UUID productVariationId;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariation> productVariations;

    @Column(name = "parent_product_id", insertable = false, updatable = false)
    private UUID parentProductId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
    private Product parentProduct;

    @Transient
    private List<Image> images;

    @Transient
    private List<Product> childProducts;
}

// product.UKq7ho6ugqk6ifti809u48w9e2q