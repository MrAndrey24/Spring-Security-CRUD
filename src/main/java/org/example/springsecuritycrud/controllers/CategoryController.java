package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public List<Category> getAllCategory() {
        try {
            return service.getAllCategories();
        } catch (Exception e) {
            throw new RuntimeException("No categories found");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        service.createCategory(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
        service.updateCategory(id, category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public void deleteCategoryById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
