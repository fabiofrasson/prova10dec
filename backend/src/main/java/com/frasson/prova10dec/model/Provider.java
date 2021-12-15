package com.frasson.prova10dec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Provider implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  private String providerName;
  private ProductType market;

  @OneToMany(mappedBy = "provider")
  @JsonIgnore
  private List<Product> products = new ArrayList<>();

  public Provider() {}

  public Provider(UUID id, String providerName, ProductType market) {
    this.id = id;
    this.providerName = providerName;
    this.market = market;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public ProductType getMarket() {
    return market;
  }

  public void setMarket(ProductType market) {
    this.market = market;
  }

  public List<Product> getProducts() {
    return products;
  }
}
