package com.adil.ProductCatelog1.Services;

import com.adil.ProductCatelog1.Models.Product;

import java.util.List;

public interface ProductService {
     public Product createProduct(Product product);
     public List<Product> getAllProducts();
     public Product getSingleProduct(Long id);
     public void deleteProduct(Long id);
     public Product patchProduct(Product product, Long id);
}
