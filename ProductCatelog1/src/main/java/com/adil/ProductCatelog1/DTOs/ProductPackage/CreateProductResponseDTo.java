package com.adil.ProductCatelog1.DTOs.ProductPackage;

import com.adil.ProductCatelog1.Models.Product;
import lombok.Data;

@Data
public class CreateProductResponseDTo {
    private ResponseProductDTO getProductDTO;



    public static CreateProductResponseDTo createProductResponseDTO(Product product){
        CreateProductResponseDTo createProductResponseDTo= new CreateProductResponseDTo();
        createProductResponseDTo.setGetProductDTO(ResponseProductDTO.getProductDTO(product));
        return createProductResponseDTo;
    }
    public Product toproduct()
    {
        return getProductDTO.toProduct();
    }

}
