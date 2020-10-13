package com.intuit.craft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.craft.demo.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}
