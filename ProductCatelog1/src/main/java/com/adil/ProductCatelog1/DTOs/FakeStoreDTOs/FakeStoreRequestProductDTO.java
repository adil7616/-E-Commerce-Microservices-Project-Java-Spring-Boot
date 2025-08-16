package com.adil.ProductCatelog1.DTOs.FakeStoreDTOs;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class FakeStoreRequestProductDTO {
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
    public static FakeStoreRequestDTO fakeStoreRequestDTO(Product product)
    {
        FakeStoreRequestDTO request=  new FakeStoreRequestDTO();
//        request.setId(product.getCategory().getId());// I have a doubt. Why it is giving the product.getcategory.getId instead of getId
        request.setTitle(product.getTitle());
        request.setCategory(product.getCategory());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());
        request.setImage(product.getImageURL());
        return  request;
    }



}
