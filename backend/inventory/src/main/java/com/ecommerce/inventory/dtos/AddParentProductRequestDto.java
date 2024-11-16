package com.ecommerce.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;
import java.util.UUID;

public record AddParentProductRequestDto(
        @NotBlank(message = "Product name is required.")
        String name,

        @NotBlank(message = "Variant is required.")
        @Size(max = 255, message = "Variant cannot exceed 255 characters.")
        String variant,

        @NotNull(message = "Category ID is required.")
        UUID categoryId,

        @NotNull(message = "Child products are required.")
        @Size(min = 1, message = "At least one child product must be provided.")
        Map<@NotNull(message = "Child product ID cannot be null.") UUID,
                @NotBlank(message = "Child product variant is required.") String> childProductIdToVariant
) {
}