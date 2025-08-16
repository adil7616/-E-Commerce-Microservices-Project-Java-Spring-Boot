package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class PatchProductRequestDTO {
    private RequestProductDTO requestProductDTO;

    public Product toproduct()
    {
        return requestProductDTO.toProduct();
    }
    public static PatchProductRequestDTO patchProductRequestDTO(Product product)
    {
        PatchProductRequestDTO patchProductRequestDTO= new PatchProductRequestDTO();
        patchProductRequestDTO.setRequestProductDTO(RequestProductDTO.requestProductDTO(product));
        return patchProductRequestDTO;
    }
}
