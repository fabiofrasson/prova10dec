package com.frasson.prova10dec.service;

import com.frasson.prova10dec.exception.ResourceAlreadyExistsException;
import com.frasson.prova10dec.exception.ResourceNotFoundException;
import com.frasson.prova10dec.model.Product;
import com.frasson.prova10dec.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

  private final ProductRepository repository;
  private final ProviderService service;

  public ProductService(ProductRepository repository, ProviderService service) {
    this.repository = repository;
    this.service = service;
  }

  public List<Product> listAll() {
    return repository.findAll();
  }

  public Product findByIdOrThrowError(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found, please try again."));
  }

  public Product findByProductName(String productName) {
    Optional<Product> foundProduct = repository.findByProductNameIgnoreCase(productName);

    if (foundProduct.isEmpty()) {
      throw new ResourceNotFoundException("Product not found.");
    }

    return foundProduct.get();
  }

  @Transactional
  public String save(Product product) {
    Optional<Product> findProduct =
        repository.findByProductNameIgnoreCase(product.getProductName());

    if (findProduct.isPresent()) {
      throw new ResourceAlreadyExistsException("Product is already registered.");
    }

    product.setId(UUID.randomUUID());
    repository.save(product);
    return "Product " + product.getProductName() + " successfully registered!";
  }

  public String delete(UUID id) {
    repository.deleteById(this.findByIdOrThrowError(id).getId());
    return "Product successfully deleted!";
  }

  public String update(UUID id, Product product) {
    Product savedProduct = this.findByIdOrThrowError(id);

    savedProduct.setId(id);
    savedProduct.setProductName(product.getProductName());
    savedProduct.setProductType(product.getProductType());

    if (service.findByProviderName(product.getProvider().getProviderName()) == null) {
      service.save(product.getProvider());
      savedProduct.setProvider(service.findByProviderName(product.getProductName()));
    } else {
      savedProduct.setProvider(service.findByProviderName(product.getProvider().getProviderName()));
    }
    savedProduct.setPurchasePrice(product.getPurchasePrice());
    savedProduct.setRetailPrice(product.getRetailPrice());

    repository.save(savedProduct);

    return "Product successfully updated!";
  }

  public String raiseStockQty(UUID id, Integer qty) {
    Product product = this.findByIdOrThrowError(id);

    product.raiseStock(qty);

    repository.save(product);

    return product.getProductName() + " stock raised by " + qty + " unit(s).";
  }

  public String lowerStockQty(UUID id, Integer qty) throws Exception {
    Product product = this.findByIdOrThrowError(id);

    product.lowerStock(qty);

    repository.save(product);

    return product.getProductName() + " stock lowered by " + qty + " unit(s).";
  }
}
