package com.my.steinfield.Steinfield.services;

import com.my.steinfield.Steinfield.models.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> index();
    Payment show(Integer id);
}
