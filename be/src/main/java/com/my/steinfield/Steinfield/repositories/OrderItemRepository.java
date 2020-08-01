package com.my.steinfield.Steinfield.repositories;

import com.my.steinfield.Steinfield.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
