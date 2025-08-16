package com.adil.ProductCatelog1.DAOs;

import com.adil.ProductCatelog1.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Product save(Product product);
}
