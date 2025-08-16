package com.adil.ProductCatelog1.DTOs.ProductPackage;


import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;
import org.springframework.web.bind.annotation.PatchMapping;

@Data
public class PatchResponseProductDTO {
    ResponseProductDTO responseProductDTO;

    public Product toProduct()
    {
        return responseProductDTO.toProduct();
    }
    public static PatchResponseProductDTO patchResponseProductDTO(Product product)
    {
        PatchResponseProductDTO patchResponseProductDTO= new PatchResponseProductDTO();
        patchResponseProductDTO.setResponseProductDTO(ResponseProductDTO.getProductDTO(product));
        return patchResponseProductDTO;
    }



}
