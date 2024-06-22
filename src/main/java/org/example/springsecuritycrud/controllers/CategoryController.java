package org.example.springsecuritycrud.controllers;

import org.example.springsecuritycrud.dto.CategoryResponse;
import org.example.springsecuritycrud.dto.GenericResponse;
import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(service.getAllCategories()));
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> createCategory(@RequestBody Category newCategory) {
        try {
            Long newId = service.createCategory(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(new CategoryResponse(newId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        try {
            if (service.get(id) != null) {
                updatedCategory.setId(id);
                service.updateCategory(updatedCategory);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Category updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Category not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> deleteCategory(@PathVariable Long id) {
        try {
            if (service.get(id) != null) {
                service.deleteCategoryById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Category deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Category not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }
}
