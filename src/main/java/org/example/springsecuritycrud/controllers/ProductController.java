package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public List<Product> getAllProducts() {
        try {
            return service.getAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("No products found");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            service.createProduct(product);
            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<String> updateProductById(@PathVariable Long id, @RequestBody Product product){
        try {
            service.updateProductById(id, product);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e){
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        try {
            service.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
