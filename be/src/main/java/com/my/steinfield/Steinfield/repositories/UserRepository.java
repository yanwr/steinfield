package com.my.steinfield.Steinfield.repositories;

import com.my.steinfield.Steinfield.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    @Transactional(readOnly = true)
    User findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<User> findById(Long id);
}