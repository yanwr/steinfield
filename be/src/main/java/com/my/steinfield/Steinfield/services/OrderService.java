package com.my.steinfield.Steinfield.services;

import org.springframework.data.domain.Page;
import java.util.List;
import com.my.steinfield.Steinfield.models.Order;

public interface OrderService {
    List<Order> index();
    Order show(Long id);
    Page<Order> findPage(
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction
    );
}