package org.example.springsecuritycrud.seeder;

import org.example.springsecuritycrud.entities.Category;
import org.example.springsecuritycrud.repository.CategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CategorySeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createCategory();
    }


    private void createCategory() {
        List<Category> categories = List.of(
                new Category("Electronics", "Devices and gadgets"),
                new Category("Clothing", "Apparel and accessories"),
                new Category("Books", "Printed and digital books"),
                new Category("Accessories", "Various accessories for devices")
        );

        categories.stream().
                filter(category -> categoryRepository.findByName(Objects.requireNonNull(category.getName())).isEmpty())
                .forEach(categoryRepository::save);
    }
}
