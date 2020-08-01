package com.my.steinfield.Steinfield.repositories;

import com.my.steinfield.Steinfield.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }