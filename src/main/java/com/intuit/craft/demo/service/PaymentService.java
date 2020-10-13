package com.intuit.craft.demo.service;

import com.intuit.craft.demo.model.dto.PaymentDTO;

public interface PaymentService {

	/**
	 * @param paymentDTO
	 * @return
	 */
	PaymentDTO processPayment(PaymentDTO paymentDTO);

}
