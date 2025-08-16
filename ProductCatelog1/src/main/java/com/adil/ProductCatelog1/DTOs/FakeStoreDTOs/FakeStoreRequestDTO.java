package com.adil.ProductCatelog1.DTOs.FakeStoreDTOs;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class FakeStoreRequestDTO {
    private String title;
    private String description;
    private Category category;
    private double price;
    private String image;

    public Product toProduct()
    {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImageURL(image);
        return product;
    }
    public static FakeStoreGetAllProductsDTO fakeStoreGetAllProductsDTO(Product product){
        FakeStoreGetAllProductsDTO request= new FakeStoreGetAllProductsDTO();
        request.setCategory(product.getCategory());
        request.setId(product.getCategory().getId());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());
        request.setTitle(product.getTitle());
        request.setImage(product.getImageURL());
        return request;
    }
}
