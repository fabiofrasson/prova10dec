package com.frasson.prova10dec.repository;

import com.frasson.prova10dec.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findByProductNameIgnoreCase(String name);
}
