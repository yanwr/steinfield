package com.my.steinfield.Steinfield.repositories;

import com.my.steinfield.Steinfield.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
