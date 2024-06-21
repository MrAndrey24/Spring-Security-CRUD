package org.example.springsecuritycrud.repository;

import jakarta.persistence.StoredProcedureParameter;
import org.example.springsecuritycrud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
