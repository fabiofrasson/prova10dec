package com.frasson.prova10dec.util;

import com.frasson.prova10dec.model.Product;
import com.frasson.prova10dec.model.ProductType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProductCreator {

  public static List<Product> createProducts() {
    Product product =
        new Product(
            UUID.randomUUID(),
            "Product A",
            ProductType.TYPE_FOOD,
            ProviderCreator.createProvider(1),
            10,
            10.0,
            15.0);
    Product product2 =
        new Product(
            UUID.randomUUID(),
            "Product B",
            ProductType.TYPE_BEVERAGE,
            ProviderCreator.createProvider(2),
            15,
            15.0,
            20.0);
    Product product3 =
        new Product(
            UUID.randomUUID(),
            "Product C",
            ProductType.TYPE_DESSERT,
            ProviderCreator.createProvider(3),
            20,
            25.0,
            30.0);
    Product product4 =
        new Product(
            UUID.randomUUID(),
            "Product D",
            ProductType.TYPE_SNACK,
            ProviderCreator.createProvider(4),
            25,
            35.0,
            40.0);
    Product product5 =
        new Product(
            UUID.randomUUID(),
            "Product E",
            ProductType.TYPE_SOUVENIR,
            ProviderCreator.createProvider(5),
            30,
            45.0,
            50.0);

    return new ArrayList<>(List.of(product, product2, product3, product4, product5));
  }
}
