package org.example.springsecuritycrud.service;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Long createCategory(Category category){
        repo.save(category);
        return category.getId();
    }

    public List<Category> getAllCategories(){
        return (List<Category>) repo.findAll();
    }
}
