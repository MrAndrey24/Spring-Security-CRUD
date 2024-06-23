package org.example.springsecuritycrud.dto;

public class ProductResponse {
    private Long id;

    public ProductResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
