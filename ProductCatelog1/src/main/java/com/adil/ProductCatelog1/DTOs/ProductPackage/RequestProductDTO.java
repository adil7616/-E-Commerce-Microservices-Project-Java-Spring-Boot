package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class RequestProductDTO {
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private String imageURL;

    public Product toProduct()
    {
        Product product= new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        Category category= new Category();
        category.setName(categoryName);
        product.setCategory(category);
        product.setImageURL(imageURL);
        return product;
    }

    public static RequestProductDTO requestProductDTO(Product product)
    {
        RequestProductDTO request= new RequestProductDTO();
        request.setTitle(product.getTitle());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());
        request.setCategoryName(product.getCategory().getName());
        request.setImageURL(product.getImageURL());
        return request;
    }

}
