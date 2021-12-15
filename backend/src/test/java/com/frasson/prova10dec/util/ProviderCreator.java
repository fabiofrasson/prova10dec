package com.frasson.prova10dec.util;

import com.frasson.prova10dec.model.ProductType;
import com.frasson.prova10dec.model.Provider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProviderCreator {

  public static Provider createProvider(int option) {
    switch (option) {
      case 1:
        return new Provider(UUID.randomUUID(), "Provider A", ProductType.TYPE_FOOD);
      case 2:
        return new Provider(UUID.randomUUID(), "Provider B", ProductType.TYPE_SNACK);
      case 3:
        return new Provider(UUID.randomUUID(), "Provider C", ProductType.TYPE_BEVERAGE);
      case 4:
        return new Provider(UUID.randomUUID(), "Provider D", ProductType.TYPE_DESSERT);
      case 5:
        return new Provider(UUID.randomUUID(), "Provider E", ProductType.TYPE_SOUVENIR);
      default:
        return new Provider(UUID.randomUUID(), "Provider F", ProductType.TYPE_SOUVENIR);
    }
  }
}
