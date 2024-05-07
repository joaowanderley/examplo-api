package com.example.demo.db.controllers;


import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequest;
import com.example.demo.usecase.ProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductUseCase productUseCase;

  public ProductController(ProductUseCase productUseCase) {
    this.productUseCase = productUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestBody Product product) {
    List<Product> listaProdutos = productUseCase.getAllProductsByCategory(product.getCategory());
    if (listaProdutos.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }
    return ResponseEntity.of(Optional.of(listaProdutos));
  }

  @PostMapping
  public ResponseEntity<Product> criarProduto(@RequestBody ProductRequest productRequest) {
    return ResponseEntity.ok(productUseCase.salvarProduto(productRequest));
  }

  @GetMapping("/lista")
  public ResponseEntity<List<Product>> listAll(@RequestBody Product product) {
    List<Product> listaProdutos = productUseCase.listAll();
    return ResponseEntity.of(Optional.of(listaProdutos));
  }
}
