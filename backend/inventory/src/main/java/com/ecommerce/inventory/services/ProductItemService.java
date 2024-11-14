package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.ProductItem;
import com.ecommerce.inventory.repositories.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductItemService {
    private final ProductItemRepository productItemRepository;

    public Optional<ProductItem> getProductItem(Long id) {
        return productItemRepository.findById(id);
    }

    public ProductItem addProductItem(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }

    public ProductItem updateProductItem(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }

    public void deleteProductItem(Long id) {
        productItemRepository.deleteById(id);
    }
}
