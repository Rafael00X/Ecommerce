package com.ecommerce.inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfMeasureType {
    PIECE("pc"),

    VOLUME_MILLILITRE("ml"),
    VOLUME_LITRE("l"),

    WEIGHT_GRAM("g"),
    WEIGHT_KILOGRAM("kg"),

    LENGTH_MILLIMETRE("mm"),
    LENGTH_CENTIMETRE("cm"),
    LENGTH_METRE("m");

    private final String code;
}
