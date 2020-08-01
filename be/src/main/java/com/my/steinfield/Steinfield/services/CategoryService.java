package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Category;
import java.util.List;

public interface CategoryService {
    List<Category> index();
    Category show(Long id);
    Category update(Long id, Category category);
    void destroy(Long id);
}