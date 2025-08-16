package com.adil.ProductCatelog1.Services;

import com.adil.ProductCatelog1.DTOs.FakeStoreDTOs.*;
import com.adil.ProductCatelog1.DTOs.ProductPackage.GetSingleProductResponseDTO;
import com.adil.ProductCatelog1.Models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("fakeStoreService")
public class ProductServicesFakeApiImpl implements ProductService{
    private RestTemplate restTemplate;

    public ProductServicesFakeApiImpl(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreRequestProductDTO request= new FakeStoreRequestProductDTO();
        request.setTitle(product.getTitle());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());
        request.setCategory(product.getCategory());
        request.setImage(product.getImageURL());
        FakeStoreResponseProductDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreResponseProductDTO.class
        );

        Product p1= new Product();
        p1.setId(response.getId());
        p1.setTitle(response.getTitle());
        p1.setCategory(response.getCategory());
        p1.setDescription(response.getDescription());
        p1.setPrice(response.getPrice());
        p1.setImageURL(response.getImage());
        return p1;
    }
    @Override
    public List<Product> getAllProducts() {
        FakeStoreGetAllProductsDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetAllProductsDTO[].class
        );

        List<Product> products = new ArrayList<>();
        if (responseArray != null) {
            for (FakeStoreGetAllProductsDTO response : responseArray) {
                Product product = FakeStoreGetAllProductsDTO.toProductDto(response);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long id) {
        GetSingleProductResponseDTO responseDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,GetSingleProductResponseDTO.class);
        Product product= new Product();

        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product patchProduct(Product product, Long id){
        return null;
    }
}
