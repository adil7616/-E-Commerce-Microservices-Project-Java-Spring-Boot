package com.adil.ProductCatelog1.DTOs.FakeStoreDTOs;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class FakeStoreGetProductResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImageURL(image);
        return product;
    }
}
