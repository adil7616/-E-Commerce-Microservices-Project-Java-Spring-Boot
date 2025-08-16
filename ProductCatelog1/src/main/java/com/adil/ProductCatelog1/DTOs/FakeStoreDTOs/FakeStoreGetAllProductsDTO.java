package com.adil.ProductCatelog1.DTOs.FakeStoreDTOs;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

import java.util.concurrent.Callable;

@Data
public class FakeStoreGetAllProductsDTO {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private String image;

    public static Product toProductDto(FakeStoreGetAllProductsDTO fakeStoreCreateProductResponseDTO)
    {
        Product product= new Product();
        product.setId(fakeStoreCreateProductResponseDTO.getId());
        product.setTitle(fakeStoreCreateProductResponseDTO.getTitle());
        product.setDescription(fakeStoreCreateProductResponseDTO.getDescription());
        product.setPrice(fakeStoreCreateProductResponseDTO.getPrice());
        product.setCategory(fakeStoreCreateProductResponseDTO.getCategory());
        product.setImageURL(fakeStoreCreateProductResponseDTO.getImage());
        return product;
    }

}
