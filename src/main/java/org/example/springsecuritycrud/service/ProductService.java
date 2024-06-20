package org.example.springsecuritycrud.service;

import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Long createProduct(Product product){
        repo.save(product);
        return product.getId();
    }

    public List<Product> getAllProducts(){
        return (List<Product>) repo.findAll();
    }
}
