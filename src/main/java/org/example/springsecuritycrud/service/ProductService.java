package org.example.springsecuritycrud.service;

import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Long createProduct(Product product){
        if (repo.findByName(product.getName()) != null && !repo.findByName(product.getName()).isEmpty()){
            throw new RuntimeException("Product with name " + product.getName() + " already exists");
        }
        repo.save(product);
        return product.getId();
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        return repo.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return repo.save(existingProduct);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return repo.save(product);
                });
    }

    public void deleteById(Long id){
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()){
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }
}
