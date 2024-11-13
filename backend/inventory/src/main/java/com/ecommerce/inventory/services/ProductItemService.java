package com.ecommerce.inventory.services;

import com.ecommerce.inventory.repositories.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductItemService {
    private final ProductItemRepository productItemRepository;
}
