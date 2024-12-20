package com.ecommerce.inventory.dev;

import com.ecommerce.inventory.models.enums.*;
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
    private final ImageRepository imageRepository;

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



        List<Image> images = List.of(
                new Image(null, "https://dummyimage.com/600x400/000/fff", ImageType.THUMBNAIL, null, null),
                new Image(null, "https://dummyimage.com/600x400/000/fff", ImageType.THUMBNAIL, null, null),
                new Image(null, "https://dummyimage.com/600x400/000/fff", ImageType.THUMBNAIL, null, null),
                new Image(null, "https://dummyimage.com/600x400/000/fff", ImageType.THUMBNAIL, null, null),
                new Image(null, "https://dummyimage.com/600x400/000/fff", ImageType.THUMBNAIL, null, null)
        );
        images = imageRepository.saveAll(images);

        // #################### Create categories ####################

        List<Category> parentCategories = List.of(
                Category.builder()
                        .name("Electronics")
                        .categoryType(CategoryType.ROOT)
                        .unitOfMeasure(null)
                        .parentCategory(null)
                        .build(),
                Category.builder()
                        .name("Decoration")
                        .categoryType(CategoryType.ROOT)
                        .unitOfMeasure(null)
                        .parentCategory(null)
                        .build()
        );
        parentCategories = parentCategories.stream().map(categoryService::addCategory).toList();

        List<Category> childCategories = List.of(
                Category.builder()
                        .name("Laptops")
                        .categoryType(CategoryType.TERMINAL)
                        .unitOfMeasure(unitOfMeasures.get(0))
                        .parentCategory(parentCategories.get(0))
                        .build(),
                Category.builder()
                        .name("Televisions")
                        .categoryType(CategoryType.TERMINAL)
                        .unitOfMeasure(unitOfMeasures.get(0))
                        .parentCategory(parentCategories.get(0))
                        .build(),
                Category.builder()
                        .name("Curtains")
                        .categoryType(CategoryType.TERMINAL)
                        .unitOfMeasure(unitOfMeasures.get(0))
                        .parentCategory(parentCategories.get(1))
                        .build()
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
                        .price(40000)
                        .images(List.of(images.get(0)))
                        .build(),
                Product.builder()
                        .name("Dell Laptop")
                        .description("Description of Dell Laptop")
                        .category(categories.get(2))
                        .stock(15)
                        .price(35000)
                        .images(List.of(images.get(1)))
                        .build(),
                Product.builder()
                        .name("Bedroom Curtain")
                        .description("Description of Bedroom Curtain")
                        .category(categories.get(2))
                        .stock(10)
                        .price(300)
                        .images(List.of(images.get(2)))
                        .build()
        );
        products = products.stream().map(productService::addProduct).toList();

        Product parentProduct = productService.addParentProduct(
                Product.builder()
                        .name("Laptop")
                        .description("Description of Laptop")
                        .category(categories.get(2))
                        .productType(ProductType.PARENT)
                        .build(),
                List.of(variations.get(0).getId()),
                List.of(products.get(0).getId(), products.get(1).getId()),
                List.of(
                        ProductVariation.builder()
                                .productId(products.get(0).getId())
                                .variationId(variations.get(0).getId())
                                .variationValueId(variationValues.get(1).getId())
                                .build(),
                        ProductVariation.builder()
                                .productId(products.get(1).getId())
                                .variationId(variations.get(0).getId())
                                .variationValueId(variationValues.get(0).getId())
                                .build()
                ));

    }
}
