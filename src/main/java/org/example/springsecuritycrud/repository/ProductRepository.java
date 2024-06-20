package org.example.springsecuritycrud.repository;

import org.example.springsecuritycrud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
}
