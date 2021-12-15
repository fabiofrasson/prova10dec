package com.frasson.prova10dec.service;

import com.frasson.prova10dec.exception.ResourceAlreadyExistsException;
import com.frasson.prova10dec.exception.ResourceNotFoundException;
import com.frasson.prova10dec.model.Provider;
import com.frasson.prova10dec.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {

  private final ProviderRepository repository;

  public ProviderService(ProviderRepository repository) {
    this.repository = repository;
  }

  public List<Provider> listAll() {
    return repository.findAll();
  }

  public Provider findByIdOrThrowError(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Provider not found, please try again."));
  }

  public Provider findByProviderName(String providerName) {
    Optional<Provider> foundProvider = repository.findByProviderNameIgnoreCase(providerName);

    if (foundProvider.isEmpty()) {
      throw new ResourceNotFoundException("Provider not found.");
    }

    return foundProvider.get();
  }

  @Transactional
  public String save(Provider provider) {
    Optional<Provider> findProvider =
        repository.findByProviderNameIgnoreCase(provider.getProviderName());

    if (findProvider.isPresent()) {
      throw new ResourceAlreadyExistsException("Provider is already registered.");
    }

    provider.setId(UUID.randomUUID());
    repository.save(provider);
    return "Provider " + provider.getProviderName() + " successfully registered!";
  }

  public String delete(UUID id) {
    repository.deleteById(this.findByIdOrThrowError(id).getId());
    return "Provider successfully deleted!";
  }

  public String update(UUID id, Provider provider) {
    Provider savedProvider = this.findByIdOrThrowError(id);

    savedProvider.setId(id);
    savedProvider.setProviderName(provider.getProviderName());
    savedProvider.setMarket(provider.getMarket());

    repository.save(savedProvider);

    return "Provider successfully updated!";
  }
}
