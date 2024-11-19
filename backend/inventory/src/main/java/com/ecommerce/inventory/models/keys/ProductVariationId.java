package com.ecommerce.inventory.models.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariationId implements Serializable {
    private UUID productId;
    private UUID variationId;
    private UUID variationValueId;
}