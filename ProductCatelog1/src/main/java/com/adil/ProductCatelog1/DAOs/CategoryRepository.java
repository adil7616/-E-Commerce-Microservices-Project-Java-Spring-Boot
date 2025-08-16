package com.adil.ProductCatelog1.DAOs;

import com.adil.ProductCatelog1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Redundant but allowed: Already inherited from JpaRepository
    @Override
    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);
}
