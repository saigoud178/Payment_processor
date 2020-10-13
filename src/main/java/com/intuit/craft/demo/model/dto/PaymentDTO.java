package com.intuit.craft.demo.model.dto;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PaymentDTO {

	private Integer paymentId;
	@NotNull(message = "firstName can not be blank or null")
	private String firstName;
	@NotNull(message = "lastName can not be blank or null")
	private String lastName;
	private String emailAddress;
	@NotNull(message = "address can not be blank or null")
	private AddressDTO address;
	@NotNull(message = "purchase can not be blank or null")
	private PurchaseDTO purchase;
}
