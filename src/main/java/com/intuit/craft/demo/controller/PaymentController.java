package com.intuit.craft.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.demo.model.dto.PaymentDTO;
import com.intuit.craft.demo.service.PaymentService;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {

	@Autowired
	PaymentService airportService;

	/**
	 * @param PaymentDTO
	 * @return
	 */
	@PostMapping(value = "/payment-info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentDTO> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {

		PaymentDTO payment = airportService.processPayment(paymentDTO);
		return new ResponseEntity<>(payment, HttpStatus.CREATED);
	}
}
