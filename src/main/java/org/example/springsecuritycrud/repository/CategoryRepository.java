package org.example.springsecuritycrud.repository;

import org.example.springsecuritycrud.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
