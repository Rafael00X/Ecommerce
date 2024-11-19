package com.ecommerce.inventory.models.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVariationId implements Serializable {
    private UUID categoryId;
    private UUID variationId;
}