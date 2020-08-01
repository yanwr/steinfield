package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.exceptions.AuthorizationException;
import com.my.steinfield.Steinfield.models.Order;
import com.my.steinfield.Steinfield.models.User;
import com.my.steinfield.Steinfield.repositories.OrderRepository;
import com.my.steinfield.Steinfield.repositories.UserRepository;
import com.my.steinfield.Steinfield.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepositoty;

	@Autowired
	private UserService userService;
	
	public List<Order> index(){
		return repository.findAll();
	}
	
	public Order show (Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS userLoged = this.userService.authenticated();
		if(userLoged == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Optional<User> user= userRepositoty.findById(userLoged.getId());
		return repository.findByClient(user, pageRequest);
	}
}