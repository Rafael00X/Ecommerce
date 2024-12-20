package com.ecommerce.inventory.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VariationType {
    COLOR("COLOR"),
    SIZE("SIZE");

    private final String code;

}
