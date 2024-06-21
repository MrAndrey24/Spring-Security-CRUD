package org.example.springsecuritycrud.service;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public void createCategory(Category category){
        repo.save(category);
    }

    public List<Category> getAllCategories(){
        return repo.findAll();
    }

    public void updateCategory(Long id, Category category){
        repo.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(category.getName());
                    existingCategory.setDescription(category.getDescription());
                    return repo.save(existingCategory);
                })
                .orElseGet(() -> {
                    category.setId(id);
                    return repo.save(category);
                });
    }

    public void deleteById(Long id){
        Optional<Category> category = repo.findById(id);
        if (category.isPresent()){
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Category with id " + id + " not found");
        }
    }
}
