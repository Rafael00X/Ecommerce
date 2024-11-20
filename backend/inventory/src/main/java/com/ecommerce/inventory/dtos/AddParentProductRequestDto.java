package com.ecommerce.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record AddParentProductRequestDto(
        @NotBlank(message = "Product name is required.")
        String name,

        @NotNull(message = "Variation IDs are required.")
        @Size(min = 1, message = "At least one variation ID must be provided.")
        List<UUID> variationIds,

        @NotNull(message = "Category ID is required.")
        UUID categoryId,

        @NotNull(message = "Child products are required.")
        @Size(min = 1, message = "At least one child product must be provided.")
        Map<@NotNull(message = "Child product ID cannot be null.") UUID,
                @NotBlank(message = "Child product variant is required.") List<ChildProductVariant>> childProductIdToVariant
) {
        public record ChildProductVariant(
                @NotNull(message = "Variant ID is required.")
                UUID variantId,
                UUID variantValueId,
                String customVariantValue
        ) {}
}