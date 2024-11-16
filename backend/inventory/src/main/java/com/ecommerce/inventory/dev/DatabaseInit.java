package com.ecommerce.inventory.dev;

import com.ecommerce.inventory.enums.ProductType;
import com.ecommerce.inventory.enums.UnitOfMeasureType;
import com.ecommerce.inventory.enums.VariationType;
import com.ecommerce.inventory.models.*;
import com.ecommerce.inventory.repositories.*;
import com.ecommerce.inventory.services.CategoryService;
import com.ecommerce.inventory.services.ProductService;
import com.ecommerce.inventory.services.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DatabaseInit {
    private final VariationRepository variationRepository;
    private final VariationValueRepository variationValueRepository;

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UnitOfMeasureService unitOfMeasureService;

    @EventListener(ApplicationReadyEvent.class)
    public void initDB() {
        // #################### Create Unit-Of-Measures ####################

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
        unitOfMeasures = unitOfMeasureService.addUnitOfMeasures(unitOfMeasures);

        // #################### Create variations ####################

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

        // #################### Create categories ####################

        List<Category> parentCategories = List.of(
                new Category(null, "Electronics", null, true, null, null),
                new Category(null, "Decoration", null, true, null, null)
        );
        parentCategories = parentCategories.stream().map(categoryService::addCategory).toList();

        List<Category> childCategories = List.of(
                new Category(null, "Laptops", VariationType.COLOR.getCode(), false, parentCategories.get(0), unitOfMeasures.get(0)),
                new Category(null, "Televisions", null, false, parentCategories.get(0), unitOfMeasures.get(0)),
                new Category(null, "Curtains", VariationType.COLOR.getCode(), false, parentCategories.get(1), unitOfMeasures.get(0))
        );
        childCategories = childCategories.stream().map(categoryService::addCategory).toList();

        List<Category> categories = new ArrayList<>(parentCategories.size() + childCategories.size());
        categories.addAll(parentCategories);
        categories.addAll(childCategories);

        // #################### Create products ####################

        List<Product> products = List.of(
                Product.builder()
                        .name("Asus Laptop")
                        .description("Description of Asus Laptop")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .price(40000)
                        .images(List.of(new Image(null, "https://dummyimage.com/600x400/000/fff", null, null)))
                        .build(),
                Product.builder()
                        .name("Dell Laptop")
                        .description("Description of Dell Laptop")
                        .category(categories.get(2))
                        .stock(15)
                        .stockThreshold(5)
                        .price(35000)
                        .images(List.of(new Image(null, "https://dummyimage.com/600x400/000/fff", null, null)))
                        .build(),
                Product.builder()
                        .name("Bedroom Curtain")
                        .description("Description of Bedroom Curtain")
                        .category(categories.get(2))
                        .stock(10)
                        .stockThreshold(5)
                        .price(300)
                        .images(List.of(new Image(null, "https://dummyimage.com/600x400/000/fff", null, null)))
                        .build()
        );
        products = products.stream().map(productService::addProduct).toList();

        Product parentProduct = productService.addParentProduct(Product.builder()
                .name("Laptop")
                .description("Description of Laptop")
                .category(categories.get(2))
                .productType(ProductType.PARENT_PRODUCT)
                .build(), Map.of(
                products.get(0).getId(), "COLOR:blue",
                products.get(1).getId(), "COLOR:red"
        ));

    }
}
