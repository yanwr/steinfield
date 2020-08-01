package com.my.steinfield.Steinfield.repositories;

import com.my.steinfield.Steinfield.models.Order;
import com.my.steinfield.Steinfield.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order , Long> {
	@Transactional(readOnly = true)
    Page<Order> findByClient(Optional<User> user, Pageable pageRequest);
}