package org.example.springsecuritycrud.service;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.repository.CategoryRepository;
import org.example.springsecuritycrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private ProductRepository productRepository;

    public Long createCategory(Category category){
        if (repo.findByName(category.getName()) != null && !repo.findByName(category.getName()).isEmpty()){
            throw new RuntimeException("Category with name " + category.getName() + " already exists");
        }
        repo.save(category);
        return category.getId();
    }

    public List<Category> getAllCategories(){
        return repo.findAll();
    }

    public Category get(Long id) {
        Optional<Category> category = repo.findById(id);
        return category.orElse(null);
    }

    public void updateCategory(Category category) {
        repo.save(category);
    }

   public void deleteCategoryById(Long id) {
    Category category = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Category with id " + id + " does not exist"));

    productRepository.deleteAll(productRepository.findByCategory(category));

    repo.deleteById(id);
}
}
