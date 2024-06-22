package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.dto.GenericResponse;
import org.example.springsecuritycrud.dto.ProductResponse;
import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(service.getAllProducts()));
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> createProduct(@RequestBody Product newProduct) {
        try {
            Long newId = service.createProduct(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(new ProductResponse(newId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            if (service.get(id) != null) {
                updatedProduct.setId(id);
                service.updateProduct(updatedProduct);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Product updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Product not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> deleteProduct(@PathVariable Long id) {
        try {
            if (service.get(id) != null) {
                service.deleteProductById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Product deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Product not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }
}
