package com.my.steinfield.Steinfield.repositories;


import com.my.steinfield.Steinfield.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> { }
