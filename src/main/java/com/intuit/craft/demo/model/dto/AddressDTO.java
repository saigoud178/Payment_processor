package com.intuit.craft.demo.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddressDTO {
	
	@NotNull(message = "street can not be blank or null")
	private String street;
	@NotNull(message = "city can not be blank or null")
	private String city;
	@NotNull(message = "province can not be blank or null")
	private String province;
	@NotNull(message = "country can not be blank or null")
	private String country;
}
