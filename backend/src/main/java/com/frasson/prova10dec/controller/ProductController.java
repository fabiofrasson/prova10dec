package com.frasson.prova10dec.controller;

import com.frasson.prova10dec.model.Product;
import com.frasson.prova10dec.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Product>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Product> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowError(id));
  }

  @GetMapping(path = "/find")
  public ResponseEntity<Product> findByProductName(
      @RequestParam("productName") String productName) {
    return ResponseEntity.ok(service.findByProductName(productName));
  }

  @PostMapping
  public ResponseEntity<String> save(@RequestBody @Valid Product product) {
    return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<String> update(
      @PathVariable("id") UUID id, @RequestBody @Valid Product product) {
    return new ResponseEntity<>(service.update(id, product), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }

  @PutMapping(path = "/raise/{id}")
  public ResponseEntity<String> raiseStockQty(
      @PathVariable("id") UUID id, @RequestParam("quantity") Integer quantity) {
    return new ResponseEntity<>(service.raiseStockQty(id, quantity), HttpStatus.OK);
  }

  @PutMapping(path = "/lower/{id}")
  public ResponseEntity<String> lowerStockQty(
      @PathVariable("id") UUID id, @RequestParam("quantity") Integer quantity) throws Exception {
    return new ResponseEntity<>(service.lowerStockQty(id, quantity), HttpStatus.OK);
  }
}
