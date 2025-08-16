package com.adil.ProductCatelog1.DTOs.FakeStoreDTOs;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class FakeStoreResponseProductDTO {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private String image;

    public Product toProduct()
    {
        Product product= new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImageURL(image);
        return product;
    }
    public static FakeStoreResponseProductDTO fakeStoreResponseProductDTO(Product product)
    {
        FakeStoreResponseProductDTO response= new FakeStoreResponseProductDTO();
        response.setId(product.getCategory().getId());// I have a doubt. Why it is giving the product.getcategory.getId instead of getId
        response.setTitle(product.getTitle());
        response.setCategory(product.getCategory());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImageURL());
        return  response;
    }

}
