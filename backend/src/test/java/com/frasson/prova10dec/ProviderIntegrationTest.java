package com.frasson.prova10dec;

import com.frasson.prova10dec.model.ProductType;
import com.frasson.prova10dec.model.Provider;
import com.frasson.prova10dec.repository.ProviderRepository;
import com.frasson.prova10dec.util.ProviderCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    classes = Prova10decApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private ProviderRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
    repository.save(ProviderCreator.createProvider(1));
    repository.save(ProviderCreator.createProvider(2));
    repository.save(ProviderCreator.createProvider(3));
    repository.save(ProviderCreator.createProvider(4));
  }

  @Test
  void shouldListAllProviders() {
    ResponseEntity<Provider[]> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/providers", Provider[].class);

    Provider[] providers = responseEntity.getBody();

    assertAll(() -> assertNotNull(providers), () -> assertEquals(4, providers.length));
  }

  @Test
  void shouldFindProviderById() {
    List<Provider> providers = repository.findAll();

    UUID id = providers.get(providers.size() - 1).getId();

    ResponseEntity<Provider> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/providers/" + id, Provider.class);

    Provider provider = responseEntity.getBody();

    assertAll(() -> assertNotNull(provider), () -> assertEquals(id, provider.getId()));
  }

  @Test
  void shouldThrowResourceNotFoundExceptionDueToNonExistentId() {
    UUID id = UUID.randomUUID();

    ResponseEntity<String> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/providers/" + id, String.class);

    int statusCodeValue = responseEntity.getStatusCodeValue();

    assertEquals(403, statusCodeValue);
  }

  @Test
  void shouldFindProviderByName() {
    String name = "Provider A";

    ResponseEntity<Provider> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/providers/find?providerName=" + name,
            Provider.class);

    Provider provider = responseEntity.getBody();

    assertAll(() -> assertNotNull(provider), () -> assertEquals(name, provider.getProviderName()));
  }

  @Test
  void shouldThrowResourceNotFoundExceptionDueToNonExistentName() {
    String name = "Product Z";

    ResponseEntity<String> responseEntity =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/providers/find?providerName=" + name, String.class);

    int statusCodeValue = responseEntity.getStatusCodeValue();

    assertEquals(403, statusCodeValue);
  }

  @Test
  void shouldSaveProviderRecord() {
    Provider provider = ProviderCreator.createProvider(5);

    ResponseEntity<String> responseEntity =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/providers", provider, String.class);

    assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  void shouldThrowResourceNotFoundExceptionDueToExistingProvider() {
    Provider provider = ProviderCreator.createProvider(1);

    ResponseEntity<String> responseEntity =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/providers", provider, String.class);

    int statusCodeValue = responseEntity.getStatusCodeValue();

    assertEquals(403, statusCodeValue);
  }

  @Test
  void shouldDeleteProviderRecord() {
    List<Provider> providers = repository.findAll();

    Provider lastProvider = providers.get(providers.size() - 1);

    UUID id = lastProvider.getId();

    this.restTemplate.delete("http://localhost:" + port + "/api/providers/" + id);

    assertEquals(3, repository.findAll().size());
  }

  @Test
  void shouldUpdateProviderRecord() {
    List<Provider> providers = repository.findAll();

    Provider lastProvider = providers.get(providers.size() - 1);

    UUID id = lastProvider.getId();

    Provider newProviderRecord =
        new Provider(lastProvider.getId(), "Updated Product", ProductType.TYPE_BEVERAGE);

    this.restTemplate.put("http://localhost:" + port + "/api/providers/" + id, newProviderRecord);

    assertAll(
        () -> assertNotEquals(lastProvider.getProviderName(), newProviderRecord),
        () -> assertNotEquals(lastProvider.getMarket(), newProviderRecord));
  }
}
