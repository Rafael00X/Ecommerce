package com.ecommerce.inventory.dev;

import com.ecommerce.inventory.enums.ProductType;
import com.ecommerce.inventory.enums.UnitOfMeasureType;
import com.ecommerce.inventory.enums.VariationType;
import com.ecommerce.inventory.models.*;
import com.ecommerce.inventory.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DatabaseInit {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final VariationRepository variationRepository;
    private final VariationValueRepository variationValueRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initDB() {
        List<UnitOfMeasure> unitOfMeasures = List.of(
                new UnitOfMeasure(null, UnitOfMeasureType.PIECE.name(), UnitOfMeasureType.PIECE.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.VOLUME_MILLILITRE.name(), UnitOfMeasureType.VOLUME_MILLILITRE.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.VOLUME_LITRE.name(), UnitOfMeasureType.VOLUME_LITRE.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.WEIGHT_GRAM.name(), UnitOfMeasureType.WEIGHT_GRAM.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.WEIGHT_KILOGRAM.name(), UnitOfMeasureType.WEIGHT_KILOGRAM.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.LENGTH_MILLIMETRE.name(), UnitOfMeasureType.LENGTH_MILLIMETRE.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.LENGTH_CENTIMETRE.name(), UnitOfMeasureType.LENGTH_CENTIMETRE.getCode()),
                new UnitOfMeasure(null, UnitOfMeasureType.LENGTH_METRE.name(), UnitOfMeasureType.LENGTH_METRE.getCode())
        );
        unitOfMeasures = unitOfMeasureRepository.saveAll(unitOfMeasures);

        List<Variation> variations = List.of(
                new Variation(null, VariationType.COLOR.name(), VariationType.COLOR.getCode()),
                new Variation(null, VariationType.SIZE.name(), VariationType.SIZE.getCode())
        );
        variations = variationRepository.saveAll(variations);

        List<VariationValue> variationValues = List.of(
                new VariationValue(null, "Red", variations.get(0)),
                new VariationValue(null, "Blue", variations.get(0)),
                new VariationValue(null, "Green", variations.get(0)),
                new VariationValue(null, "S", variations.get(1)),
                new VariationValue(null, "M", variations.get(1)),
                new VariationValue(null, "L", variations.get(1)),
                new VariationValue(null, "XL", variations.get(1))
        );
        variationValues = variationValueRepository.saveAll(variationValues);

        List<Category> categories = new ArrayList<>(List.of(
                new Category(null, "Electronics", null, true, null, null),
                new Category(null, "Decoration", null, true, null, null)
        ));
        categories = categoryRepository.saveAll(categories);
        categories.addAll(List.of(
                new Category(null, "Laptops", null, false, categories.get(0), unitOfMeasures.get(0)),
                new Category(null, "Televisions", null, false, categories.get(0), unitOfMeasures.get(0)),
                new Category(null, "Curtains", VariationType.COLOR.getCode(), false, categories.get(1), unitOfMeasures.get(1))
        ));
        categories = categoryRepository.saveAll(categories);

        Product parentProduct = productRepository.save(
                Product.builder()
                        .name("Laptop")
                        .description("Description of Laptop")
                        .category(categories.get(2))
                        .productType(ProductType.PARENT_PRODUCT)
                        .build());

        List<Product> products = List.of(
                Product.builder()
                        .name("Asus Laptop")
                        .description("Description of Asus Laptop")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .price(40000)
                        .variant("COLOR:blue")
                        .productType(ProductType.CHILD_PRODUCT)
                        .parentProduct(parentProduct)
                        .build(),
                Product.builder()
                        .name("Dell Laptop")
                        .description("Description of Dell Laptop")
                        .category(categories.get(2))
                        .stock(15)
                        .stockThreshold(5)
                        .price(35000)
                        .variant("COLOR:red")
                        .productType(ProductType.CHILD_PRODUCT)
                        .parentProduct(parentProduct)
                        .build(),
                Product.builder()
                        .name("Bedroom Curtain")
                        .description("Description of Bedroom Curtain")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .price(300)
                        .variant("COLOR:blue")
                        .productType(ProductType.INDEPENDENT_PRODUCT)
                        .parentProduct(null)
                        .build()
        );
        products = productRepository.saveAll(products);

    }
}
