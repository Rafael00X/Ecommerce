package com.ecommerce.inventory.services;

import com.ecommerce.inventory.models.ProductVariation;
import com.ecommerce.inventory.models.enums.ProductType;
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
            if (product.getProductType() == ProductType.PARENT) {
                product.setChildProducts(new ArrayList<>());
                productMap.put(product.getId(), product);
            }
        }
        for (Product product : products) {
            if (product.getProductType() == ProductType.CHILD) {
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
        product.setProductType(ProductType.INDEPENDENT);
        product.setParentProduct(null);
        product.setThumbnailImage(product.getImages().get(0));
        Product savedProduct = productRepository.save(product);
        List<Image> images = product.getImages();
        for (Image image : images) {
            image.setProductId(savedProduct.getId());
        }
        savedProduct.setImages(imageService.addImages(images));
        return product;
    }

    public Product addParentProduct(
            Product parentProduct,
            List<UUID> parentProductVariationIds,
            List<UUID> childProductIds,
            List<ProductVariation> childProductVariations) {
        parentProduct.setProductType(ProductType.PARENT);
        Product savedParentProduct = productRepository.save(parentProduct);

        List<ProductVariation> parentProductVariations = parentProductVariationIds.stream().map(
                variationId -> ProductVariation.builder()
                        .productId(savedParentProduct.getId())
                        .variationId(variationId)
                        .build()
        ).toList();

        List<Product> childProducts = productRepository.findAllById(childProductIds);
        List<Product> updatedChildProducts = new ArrayList<>(childProducts.size());

        for (Product childProduct : childProducts) {
            childProduct.setParentProduct(parentProduct);
            childProduct.setProductType(ProductType.CHILD);
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
