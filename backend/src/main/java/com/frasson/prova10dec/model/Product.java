package com.frasson.prova10dec.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  private String productName;

  private ProductType productType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "providerId")
  private Provider provider;

  private Integer stockQty;

  private Double purchasePrice;

  private Double retailPrice;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public Integer getStockQty() {
    return stockQty;
  }

  public Double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public Double getRetailPrice() {
    return retailPrice;
  }

  public void setRetailPrice(Double retailPrice) {
    this.retailPrice = retailPrice;
  }

  public void raiseStock(Integer quantity) {
    this.stockQty = this.stockQty + quantity;
  }

  public void lowerStock(Integer quantity) throws Exception {
    if (this.stockQty > 0 && this.stockQty >= quantity) {
      this.stockQty = this.stockQty - quantity;
    } else {
      throw new Exception("Current stock quantity is not enough to perform operation.");
    }
  }
}
