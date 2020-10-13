package com.intuit.craft.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "payment_info")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Date createDate;
	private String street;
	private String city;
	private String province;
	private String country;
	private String item;
	private Double amount;

	@PrePersist
	public void prePersist() {
		createDate = new Date();
	}
}
