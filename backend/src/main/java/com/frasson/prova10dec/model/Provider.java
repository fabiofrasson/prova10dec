package com.frasson.prova10dec.model;

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

  private String supplierName;
  private ProductType market;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
  private List<Product> products = new ArrayList<>();
}
