package com.intuit.craft.demo.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PurchaseDTO {
	
	@NotNull(message = "item can not be blank or null")
	private String item;
	@NotNull(message = "amount can not be blank or null")
	private Double amount;
}
