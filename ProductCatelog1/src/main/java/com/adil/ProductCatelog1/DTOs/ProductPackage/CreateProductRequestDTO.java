package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class CreateProductRequestDTO {
    private RequestProductDTO productDTO;

    public Product toProduct() {
        if (productDTO == null) {
            throw new IllegalArgumentException("productDTO is null");
        }
        return productDTO.toProduct();
    }

    public static CreateProductRequestDTO createProductRequestDTo(Product product) {
        CreateProductRequestDTO requestDTo = new CreateProductRequestDTO();
        requestDTo.setProductDTO(RequestProductDTO.requestProductDTO(product));
        return requestDTo;
    }
}
