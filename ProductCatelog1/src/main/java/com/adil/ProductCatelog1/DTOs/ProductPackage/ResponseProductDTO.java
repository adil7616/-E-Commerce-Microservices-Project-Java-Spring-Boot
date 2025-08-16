package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class ResponseProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private String imageURL;

    public Product toProduct()
    {
        Product product= new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        Category category= new Category();
        category.setName(categoryName);
        product.setCategory(category);
        product.setImageURL(imageURL);
        return product;
    }

    public static ResponseProductDTO getProductDTO(Product product)
    {
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.setId(product.getId());
        responseProductDTO.setTitle(product.getTitle());
        responseProductDTO.setDescription(product.getDescription());
        responseProductDTO.setPrice(product.getPrice());
        responseProductDTO.setCategoryName(product.getCategory().getName());
        responseProductDTO.setImageURL(product.getImageURL());
        return responseProductDTO;
    }
}
