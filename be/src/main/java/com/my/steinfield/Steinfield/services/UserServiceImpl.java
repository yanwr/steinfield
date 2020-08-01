package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.User;
import com.my.steinfield.Steinfield.repositories.UserRepository;
import com.my.steinfield.Steinfield.security.UserSS;
import com.my.steinfield.Steinfield.exceptions.DataInvalidException;
import com.my.steinfield.Steinfield.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> index() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public User show(Long id) {
        try {
            Optional<User> user = repository.findById(id);
            return user.orElseThrow(() -> new DataNotFoundException(id.toString()));
        } catch (DataNotFoundException | EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new DataInvalidException(e.getMessage(), e);
        }
    }

    @Override
    public User store(User user) {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Long id, User user) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean destroy(Long id) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch(Exception e) {
            return null;
        }
    }

    private boolean hasCurrentUser(Long id) {
        try{
            Optional<User> currentUser = repository.findById(id);
            return currentUser.isPresent();
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new DataNotFoundException(id.toString());
        }
    }
}