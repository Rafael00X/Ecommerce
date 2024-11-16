package com.ecommerce.inventory.dtos;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record AddProductRequestDto(
        @NotBlank(message = "Product name is required.")
        String name,

        @Size(max = 1000, message = "Description cannot exceed 255 characters.")
        String description,

        @NotNull(message = "Stock is required.")
        @Min(value = 0, message = "Stock cannot be negative.")
        Integer stock,

        @NotNull(message = "Stock threshold is required.")
        @Min(value = 0, message = "Stock threshold cannot be negative.")
        Integer stockThreshold,

        @NotNull(message = "Price is required.")
        @Positive(message = "Price must be greater than zero.")
        Integer price,

        @NotNull(message = "Category ID is required.")
        @Positive(message = "Category ID must be a positive value.")
        UUID categoryId
) {}
