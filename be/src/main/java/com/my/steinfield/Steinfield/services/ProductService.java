package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Product;
import java.util.List;

public interface ProductService {
    List<Product> index();
    Product show(Long id);
    Product store(Product product);
    Product update(Long id, Product product);
    void destroy(Long id);
}