package com.frasson.prova10dec.controller;

import com.frasson.prova10dec.model.Provider;
import com.frasson.prova10dec.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

  private ProviderService service;

  public ProviderController(ProviderService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Provider>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Provider> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowError(id));
  }

  @GetMapping(path = "/find")
  public ResponseEntity<Provider> findByProductName(
      @RequestParam("providerName") String providerName) {
    return ResponseEntity.ok(service.findByProviderName(providerName));
  }

  @PostMapping
  public ResponseEntity<String> save(@RequestBody @Valid Provider provider) {
    return new ResponseEntity<>(service.save(provider), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<String> update(
      @PathVariable("id") UUID id, @RequestBody @Valid Provider provider) {
    return new ResponseEntity<>(service.update(id, provider), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }
}
