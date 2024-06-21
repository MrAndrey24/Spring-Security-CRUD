package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public Long addProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product){
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public void deleteProductById(@PathVariable Long id){
        service.deleteById(id);
    }
}
