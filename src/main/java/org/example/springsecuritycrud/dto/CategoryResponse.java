package org.example.springsecuritycrud.dto;

public class CategoryResponse {
    private Long id;

    public CategoryResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
