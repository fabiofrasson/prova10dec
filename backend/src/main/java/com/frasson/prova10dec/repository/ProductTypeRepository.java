package com.frasson.prova10dec.repository;

import com.frasson.prova10dec.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {}
