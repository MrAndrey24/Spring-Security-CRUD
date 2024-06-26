package org.example.springsecuritycrud.repository;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
