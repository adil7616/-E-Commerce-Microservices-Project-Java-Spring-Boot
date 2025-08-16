package com.adil.ProductCatelog1.Controllers;


import com.adil.ProductCatelog1.DTOs.ProductPackage.*;
import com.adil.ProductCatelog1.Models.Product;
import com.adil.ProductCatelog1.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductCatalogControllers {

    private final ProductService productService;
    public ProductCatalogControllers(@Qualifier("DBService") ProductService productService)
    {
        this.productService=productService;
    }

    @PostMapping("/")
    public CreateProductResponseDTo createProduct(@RequestBody CreateProductRequestDTO request){
        Product product = productService.createProduct(request.toProduct());
        return CreateProductResponseDTo.createProductResponseDTO(product);
    }


    @GetMapping("")
    public GetAllProductResponseDTO get_AllProducts(){
        List<Product> products= productService.getAllProducts();
        GetAllProductResponseDTO responseDTO= new GetAllProductResponseDTO();
        responseDTO.setProducts(new ArrayList<>());
        for(Product product: products)
        {
            ResponseProductDTO responseProductDTO = ResponseProductDTO.getProductDTO(product);
            responseDTO.getProducts().add(responseProductDTO);
        }
        return responseDTO;
     }
    @GetMapping("/{id}")
    public GetSingleProductResponseDTO getProduct(@PathVariable("id") Long productId)
    {
        Product product= productService.getSingleProduct(productId);
        return GetSingleProductResponseDTO.fromProduct(product);
    }
    @DeleteMapping("/{id}")
    public boolean isDelete(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return true;
    }
    @PatchMapping("/{id}")
    public PatchResponseProductDTO patchProduct(@RequestBody PatchProductRequestDTO patchProductRequestDTO,@PathVariable("id") Long productId)
    {
        Product product = productService.patchProduct(patchProductRequestDTO.toproduct(),productId);
        return PatchResponseProductDTO.patchResponseProductDTO(product);
    }

}
