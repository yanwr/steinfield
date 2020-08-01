package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Category;
import com.my.steinfield.Steinfield.repositories.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> index() {
        return repository.findAll();
    }

    public Category show(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new DataNotFoundException(id.toString()));
    }

    public Category update(Long id, Category category) {
        try {
            return repository.save(category);
        } catch (EntityNotFoundException e) {
            throw new DataNotFoundException(id.toString());
        }
        catch(DataIntegrityViolationException e){
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