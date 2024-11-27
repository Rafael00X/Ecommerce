package com.ecommerce.inventory.models;

import com.ecommerce.inventory.models.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Column(name = "thumbnail_image_id", insertable = false, updatable = false)
    private UUID thumbnailImageId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thumbnail_image_id", referencedColumnName = "id")
    private Image thumbnailImage;

    @Column(name = "category_id", insertable = false, updatable = false)
    private UUID categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "parent_product_id", insertable = false, updatable = false)
    private UUID parentProductId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
    private Product parentProduct;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariation> productVariations;

    @OneToMany(mappedBy = "parentProduct", fetch = FetchType.LAZY)
    private List<Product> childProducts;

    public enum Field {
        THUMBNAIL_IMAGE,
        CATEGORY,
        IMAGES,
        PRODUCT_VARIATIONS,
        CHILD_PRODUCTS
    }

    @Builder.Default
    public final Set<Field> includedFields = new HashSet<>(Set.of(Field.THUMBNAIL_IMAGE));

    public Image getThumbnailImage() {
        includedFields.add(Field.THUMBNAIL_IMAGE);
        return thumbnailImage;
    }

    public Category getCategory() {
        includedFields.add(Field.CATEGORY);
        return category;
    }

    public List<Image> getImages() {
        includedFields.add(Field.IMAGES);
        return images;
    }

    public List<ProductVariation> getProductVariations() {
        includedFields.add(Field.PRODUCT_VARIATIONS);
        return productVariations;
    }

    public List<Product> getChildProducts() {
        includedFields.add(Field.CHILD_PRODUCTS);
        return childProducts;
    }

}