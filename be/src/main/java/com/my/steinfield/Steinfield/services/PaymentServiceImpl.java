package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Payment;
import com.my.steinfield.Steinfield.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	public List<Payment> index() {
		return repository.findAll();
	}
	
	public Payment show(Integer id) {
		Optional<Payment> obj = repository.findById(id);
		return obj.get();
	}
}