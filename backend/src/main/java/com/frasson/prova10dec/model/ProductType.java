package com.frasson.prova10dec.model;

public enum ProductType {
  TYPE_FOOD,
  TYPE_BEVERAGE,
  TYPE_SNACK,
  TYPE_DESSERT,
  TYPE_SOUVENIR;

  public String getStatus() {
    return this.name();
  }
}
