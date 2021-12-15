package com.frasson.prova10dec.repository;

import com.frasson.prova10dec.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {
  Optional<Provider> findByProviderNameIgnoreCase(String name);
}
