package com.ecommerce.inventory.dev;

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
        unitOfMeasureRepository.deleteAll();
        variationValueRepository.deleteAll();
        variationRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        List<UnitOfMeasure> unitOfMeasures = List.of(
                new UnitOfMeasure(1L, UnitOfMeasureType.PIECE.name(), UnitOfMeasureType.PIECE.getCode()),
                new UnitOfMeasure(2L, UnitOfMeasureType.VOLUME_MILLILITRE.name(), UnitOfMeasureType.VOLUME_MILLILITRE.getCode()),
                new UnitOfMeasure(3L, UnitOfMeasureType.VOLUME_LITRE.name(), UnitOfMeasureType.VOLUME_LITRE.getCode()),
                new UnitOfMeasure(4L, UnitOfMeasureType.WEIGHT_GRAM.name(), UnitOfMeasureType.WEIGHT_GRAM.getCode()),
                new UnitOfMeasure(5L, UnitOfMeasureType.WEIGHT_KILOGRAM.name(), UnitOfMeasureType.WEIGHT_KILOGRAM.getCode()),
                new UnitOfMeasure(6L, UnitOfMeasureType.LENGTH_MILLIMETRE.name(), UnitOfMeasureType.LENGTH_MILLIMETRE.getCode()),
                new UnitOfMeasure(7L, UnitOfMeasureType.LENGTH_CENTIMETRE.name(), UnitOfMeasureType.LENGTH_CENTIMETRE.getCode()),
                new UnitOfMeasure(8L, UnitOfMeasureType.LENGTH_METRE.name(), UnitOfMeasureType.LENGTH_METRE.getCode())
        );
        unitOfMeasures = unitOfMeasureRepository.saveAll(unitOfMeasures);

        List<Variation> variations = List.of(
                new Variation(1L, VariationType.COLOR.name(), VariationType.COLOR.getCode()),
                new Variation(2L, VariationType.SIZE.name(), VariationType.SIZE.getCode())
        );
        variations = variationRepository.saveAll(variations);

        List<VariationValue> variationValues = List.of(
                new VariationValue(1L, "Red", variations.get(0)),
                new VariationValue(2L, "Blue", variations.get(0)),
                new VariationValue(3L, "Green", variations.get(0)),
                new VariationValue(4L, "S", variations.get(1)),
                new VariationValue(5L, "M", variations.get(1)),
                new VariationValue(6L, "L", variations.get(1)),
                new VariationValue(7L, "XL", variations.get(1))
        );
        variationValues = variationValueRepository.saveAll(variationValues);

        List<Category> categories = new ArrayList<>(List.of(
                new Category(1L, "Electronics", null, true, null, null),
                new Category(2L, "Decoration", null, true, null, null)
        ));
        categories = categoryRepository.saveAll(categories);
        categories.addAll(List.of(
                new Category(3L, "Laptops", null, false, categories.get(0), unitOfMeasures.get(0)),
                new Category(4L, "Televisions", null, false, categories.get(0), unitOfMeasures.get(0)),
                new Category(5L, "Curtains", VariationType.COLOR.getCode(), false, categories.get(1), unitOfMeasures.get(1))
        ));
        categories = categoryRepository.saveAll(categories);

        Product parentProduct = productRepository.save(
                Product.builder()
                        .name("Laptop")
                        .description("Description of Laptop")
                        .category(categories.get(2))
                        .isParentProduct(true)
                        .build());

        List<Product> products = List.of(
                Product.builder()
                        .name("Asus Laptop")
                        .description("Description of Asus Laptop")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .listingPrice(40000)
                        .variation("COLOR:blue")
                        .isParentProduct(false)
                        .parentProduct(parentProduct)
                        .build(),
                Product.builder()
                        .name("Dell Laptop")
                        .description("Description of Dell Laptop")
                        .category(categories.get(2))
                        .stock(15)
                        .stockThreshold(5)
                        .listingPrice(35000)
                        .variation("COLOR:red")
                        .isParentProduct(false)
                        .parentProduct(parentProduct)
                        .build(),
                Product.builder()
                        .name("Asus Laptop")
                        .description("Description of Asus Laptop")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .listingPrice(40000)
                        .variation("COLOR:blue")
                        .isParentProduct(false)
                        .parentProduct(null)
                        .build()
        );
        products = productRepository.saveAll(products);

    }
}
