package com.ecommerce.inventory.services;

import com.ecommerce.inventory.enums.ProductType;
import com.ecommerce.inventory.models.Image;
import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public List<Product> arrangeProductsInHierarchy(List<Product> products) {
        Map<UUID, Product> productMap = new HashMap<>();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductType() == ProductType.PARENT_PRODUCT) {
                product.setChildProducts(new ArrayList<>());
                productMap.put(product.getId(), product);
            }
        }
        for (Product product : products) {
            if (product.getProductType() == ProductType.CHILD_PRODUCT) {
                productMap.get(product.getParentProductId()).getChildProducts().add(product);
            } else {
                result.add(product);
            }
        }
        return result;
    }

    public Optional<Product> getProduct(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return arrangeProductsInHierarchy(products);
    }

    public Product addProduct(Product product) {
        product.setProductType(ProductType.INDEPENDENT_PRODUCT);
        product.setParentProduct(null);
        product.setThumbnailUrl(product.getImages().get(0).getUrl());
        Product savedProduct = productRepository.save(product);
        List<Image> images = product.getImages();
        for (Image image : images) {
            image.setProductId(savedProduct.getId());
        }
        savedProduct.setImages(imageService.addImages(images));
        return product;
    }

    public Product addParentProduct(Product product, Map<UUID, String> childProductIdToVariant) {
        product.setProductType(ProductType.PARENT_PRODUCT);
        Product parentProduct = productRepository.save(product);

        List<Product> childProducts = productRepository.findAllById(childProductIdToVariant.keySet());
        List<Product> updatedChildProducts = new ArrayList<>(childProducts.size());

        for (Product childProduct : childProducts) {
            childProduct.setParentProduct(parentProduct);
            childProduct.setProductType(ProductType.CHILD_PRODUCT);
            childProduct.setVariant(childProductIdToVariant.get(childProduct.getId()));
            updatedChildProducts.add(childProduct);
        }

        updatedChildProducts = productRepository.saveAll(updatedChildProducts);
        parentProduct.setChildProducts(updatedChildProducts);
        return parentProduct;
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
