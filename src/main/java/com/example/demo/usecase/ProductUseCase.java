package com.example.demo.usecase;

import com.example.demo.db.repository.ProductRepository;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase {

  private final ProductRepository productRepository;

  public ProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProductsByCategory(String category) {
    List<Product> listProducts = productRepository.findByCategory(category);
    if (listProducts.isEmpty()) {
      System.out.println("nao encontrou nada");
    }
    return listProducts;
  }

  public List<Product> listAll() {
    List<Product> listProducts = productRepository.findAll();
    if (listProducts.isEmpty()) {
      System.out.println("nao encontrou nada");
    }
    return listProducts;
  }

  public Product salvarProduto(ProductRequest productRequest) {
    Product productSave = new Product();
    productSave.setName(productRequest.getName());
    productSave.setPrice(productRequest.getPrice());
    productSave.setCategory(productRequest.getCategory());

    if (productRepository.existsByName(productSave.getName())) {
      System.out.println("nao pode cadastrar, pois ja existe produto com esse nome");
      return null; // estou retornando null por nao ter implementado uma excecao
    }
    return productRepository.save(productSave);
  }

}
