package com.perscholas.app.service;

import com.perscholas.app.model.Product;
import com.perscholas.app.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

@Autowired
    public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
    }


    public List<Product> findAll() {
    System.out.println("All products" +productRepository.findAll());
    List<Product> product = productRepository.findAll();
   // List<Product> productByCategoryId = productRepository.findAllByCategory_Id(5);
  // System.out.println("All products belonging to category id 5" +productRepository.findAllByCategory_Id(5));
    return product;
    }

    public Optional<Product> getProductById(Integer id) {
    return productRepository.findById(id);
    }

    public void deleteProductById(Integer id){
         productRepository.deleteById(id);
    }

 public List<Product> getAllProductsByCategoryId(int id){
    System.out.println("find by category id"+productRepository.findAllByCategory_Id(id));
    return productRepository.findAllByCategory_Id(id);

    }

    public List<Product> findByName(String keyword) {
        return productRepository.findAllByName(keyword);
    }

    public void saveUpdatedProductById(Product product) {
        productRepository.save(product);
        log.warn("saved product"+product);
    }

    public Product findById(Integer id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
