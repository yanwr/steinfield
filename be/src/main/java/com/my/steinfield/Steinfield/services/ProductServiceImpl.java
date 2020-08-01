package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Product;
import com.my.steinfield.Steinfield.repositories.ProductRepository;
import com.my.steinfield.Steinfield.exceptions.DataInvalidException;
import com.my.steinfield.Steinfield.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> index() {
        return repository.findAll();
    }

    public Product show(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new DataNotFoundException(id.toString()));
    }

    public Product store(Product newProduct) {
        return null;
    }

    public Product update(Long id, Product product) {
        try {
            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new DataNotFoundException(id.toString());
        } catch (DataIntegrityViolationException e) {
            throw new DataInvalidException(e.getMessage(), e);
        }
    }

    public void destroy(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException(id.toString());
        } catch (DataIntegrityViolationException e) {
            throw new DataInvalidException(e.getMessage(), e);
        }
    }
}