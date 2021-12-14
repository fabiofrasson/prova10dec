package com.frasson.prova10dec.service;

import com.frasson.prova10dec.exception.ResourceAlreadyExistsException;
import com.frasson.prova10dec.exception.ResourceNotFoundException;
import com.frasson.prova10dec.model.Product;
import com.frasson.prova10dec.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  public List<Product> listAll() {
    return repository.findAll();
  }

  public Product findByIdOrThrowError(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found, please try again."));
  }

  @Transactional
  public String save(Product product) {
    Optional<Product> findProduct =
        repository.findByProductNameIgnoreCase(product.getProductName());

    if (findProduct.isPresent()) {
      throw new ResourceAlreadyExistsException("Product is already registered.");
    }

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
    savedProduct.setProvider(product.getProvider());
    savedProduct.setPurchasePrice(product.getPurchasePrice());
    savedProduct.setRetailPrice(product.getRetailPrice());

    repository.save(savedProduct);

    return "Product successfully updated!";
  }
}
