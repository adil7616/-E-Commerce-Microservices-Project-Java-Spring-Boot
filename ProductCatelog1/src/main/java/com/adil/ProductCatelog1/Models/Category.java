package com.adil.ProductCatelog1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @OneToMany
    private List<Product> featuredProduct;
}
