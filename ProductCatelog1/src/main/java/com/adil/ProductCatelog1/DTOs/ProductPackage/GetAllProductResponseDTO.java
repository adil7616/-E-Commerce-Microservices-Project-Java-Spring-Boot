package com.adil.ProductCatelog1.DTOs.ProductPackage;

import lombok.Data;

import java.util.List;

@Data
public class GetAllProductResponseDTO {
    private List<ResponseProductDTO> products;
}
