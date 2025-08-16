package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class GetSingleProductResponseDTO {
    private ResponseProductDTO getDto;
    Product toProduct()
    {
        return getDto.toProduct();
    }
    public static GetSingleProductResponseDTO fromProduct(Product product) {
        GetSingleProductResponseDTO response = new GetSingleProductResponseDTO();
        ResponseProductDTO dto = new ResponseProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setCategoryName(product.getCategory().getName());
        dto.setPrice(product.getPrice());
        dto.setImageURL(product.getImageURL());
        response.setGetDto(dto);
        return response;
    }
}
