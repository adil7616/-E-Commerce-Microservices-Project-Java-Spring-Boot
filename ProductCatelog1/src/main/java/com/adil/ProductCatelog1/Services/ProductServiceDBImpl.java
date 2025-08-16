package com.adil.ProductCatelog1.Services;

import com.adil.ProductCatelog1.DAOs.CategoryRepository;
import com.adil.ProductCatelog1.DAOs.ProductRepository;
import com.adil.ProductCatelog1.Models.Category;
import com.adil.ProductCatelog1.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DBService")
public class ProductServiceDBImpl implements ProductService{

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    public ProductServiceDBImpl(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> category= categoryRepository.findByName(product.getCategory().getName());
        if(category.isEmpty())
        {
            Category category1= new Category();
            category1.setName(product.getCategory().getName());
            Category categoryToBeSaved= categoryRepository.save(category1);
            product.setCategory(categoryToBeSaved);
        }
        else{
            product.setCategory(category.get());
        }
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optionalProduct= repository.findById(id);
        if(optionalProduct.isEmpty())
        {
            throw new RuntimeException("product is not found");
        }
        return optionalProduct.get();
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product patchProduct(Product product, Long id) {
        Optional<Product>product1= repository.findById(id);
        if(product1.isEmpty())
        {
            throw new RuntimeException("Product is not found");
        }
        Product productToBeUpdated= product1.get();
        if(product.getTitle()!=null)
        {
            productToBeUpdated.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null)
        {
            productToBeUpdated.setDescription(product.getDescription());
        }
        if(product.getPrice()!= 0)
        {
            productToBeUpdated.setPrice(product.getPrice());
        }
        if(product.getCategory().getName() !=null)
        {
            Optional<Category>category= categoryRepository.findByName(product.getCategory().getName());
            if(category.isEmpty())
            {
                Category category1= new Category();
                category1.setName(product.getCategory().getName());
                Category categoryToBeSaved= categoryRepository.save(category1);
                productToBeUpdated.setCategory(categoryToBeSaved);
            }
            else {
                productToBeUpdated.setCategory(category.get());
            }
        }
        if(product.getImageURL()!=null)
        {
            productToBeUpdated.setImageURL(product.getImageURL());
        }
        if(product.getId()!=null)
        {
            productToBeUpdated.setId(product.getId());
        }
        return repository.save(productToBeUpdated);
    }


}
