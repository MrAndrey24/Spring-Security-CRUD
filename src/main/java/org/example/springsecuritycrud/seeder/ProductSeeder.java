package org.example.springsecuritycrud.seeder;

import org.example.springsecuritycrud.entities.Product;
import org.example.springsecuritycrud.repository.CategoryRepository;
import org.example.springsecuritycrud.repository.ProductRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private  final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createProducts();
    }

    private void createProducts() {
        List<Product> products = List.of(
                new Product("Smartphone", "Latest model with advanced features", 699.99, 50, categoryRepository.findByName("Electronics").getFirst()),
                new Product("Laptop", "High-performance laptop for work and play", 1299.99, 30, categoryRepository.findByName("Electronics").getFirst()),
                new Product("Wireless Headphones", "Noise-cancelling headphones with great sound quality", 199.99, 100, categoryRepository.findByName("Accessories").getFirst()),
                new Product("E-Book Reader", "Portable device for reading digital books", 129.99, 75, categoryRepository.findByName("Books").getFirst()),
                new Product("T-Shirt", "Comfortable cotton t-shirt", 19.99, 200, categoryRepository.findByName("Clothing").getFirst())
        );

        products.stream()
                .filter(product -> productRepository.findByName(product.getName()).isEmpty())
                .forEach(productRepository::save);

    }
}
