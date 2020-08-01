package com.my.steinfield.Steinfield.controllers;

import com.my.steinfield.Steinfield.models.Order;
import com.my.steinfield.Steinfield.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById (@PathVariable Long id){
		Order order = this.service.show(id);
		return ResponseEntity.ok().body(order);
	}

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<Page<Order>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="moment") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Order> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}