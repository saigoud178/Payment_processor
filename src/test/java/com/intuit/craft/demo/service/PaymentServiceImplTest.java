package com.intuit.craft.demo.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.craft.demo.configuration.CommonConfiguration;
import com.intuit.craft.demo.model.Payment;
import com.intuit.craft.demo.model.dto.AddressDTO;
import com.intuit.craft.demo.model.dto.PaymentDTO;
import com.intuit.craft.demo.model.dto.PurchaseDTO;
import com.intuit.craft.demo.repository.PaymentRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaymentServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private CommonConfiguration commonConfiguration;
	
	@InjectMocks
	PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	private ObjectMapper objectMapper;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Test(expected = NullPointerException.class)
	public void processPaymentNullObjectTest() throws NullPointerException, Exception {
		Mockito.when(commonConfiguration.getPaymentApiUrl()).thenReturn("url");
		Mockito.when(commonConfiguration.getConnectionTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getReadTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getAccessToken()).thenReturn("accessToken");
		Mockito.when(restTemplate.getRequestFactory()).thenReturn(new SimpleClientHttpRequestFactory());
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setFirstName("John");
		paymentDTO.setLastName("Doe");
		paymentDTO.setEmailAddress("john.doe@gmail.com");
		paymentDTO.getAddress().setStreet("1234 Given Lane");
		paymentDTO.getAddress().setCity("Toronto");
		paymentDTO.getAddress().setProvince("ON");
		paymentDTO.getAddress().setCountry("Canada");
		paymentDTO.getPurchase().setItem("Hat");
		paymentDTO.getPurchase().setAmount(30.00);
		
		PaymentDTO response= paymentServiceImpl.processPayment(paymentDTO);
		assertNotNull(response);
	}
	
	@Test(expected = Exception.class)
	public void processPaymentResponseNullTest() throws Exception {
		Mockito.when(commonConfiguration.getPaymentApiUrl()).thenReturn("url");
		Mockito.when(commonConfiguration.getConnectionTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getReadTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getAccessToken()).thenReturn("accessToken");
		Mockito.when(restTemplate.getRequestFactory()).thenReturn(new SimpleClientHttpRequestFactory());
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setFirstName("John");
		paymentDTO.setLastName("Doe");
		paymentDTO.setEmailAddress("john.doe@gmail.com");
		AddressDTO address = new AddressDTO();
		address.setStreet("1234 Given Lane");
		address.setCity("Toronto");
		address.setProvince("ON");
		address.setCountry("Canada");
		paymentDTO.setAddress(address);
		PurchaseDTO purchase = new PurchaseDTO(); 
		purchase.setItem("Hat");
		purchase.setAmount(30.00);
		paymentDTO.setPurchase(purchase);
		PaymentDTO response= paymentServiceImpl.processPayment(paymentDTO);
		assertNotNull(response);
	}
	
	@Test
	public void processPaymentTest() throws Exception {
		Mockito.when(commonConfiguration.getPaymentApiUrl()).thenReturn("url");
		Mockito.when(commonConfiguration.getConnectionTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getReadTimeOut()).thenReturn(5000);
		Mockito.when(commonConfiguration.getAccessToken()).thenReturn("accessToken");
		Mockito.when(restTemplate.getRequestFactory()).thenReturn(new SimpleClientHttpRequestFactory());
		Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("request");
		Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(save());
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.<Class<String>>any()))
		.thenReturn(restTempResponse());
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setFirstName("John");
		paymentDTO.setLastName("Doe");
		paymentDTO.setEmailAddress("john.doe@gmail.com");
		AddressDTO address = new AddressDTO();
		address.setStreet("1234 Given Lane");
		address.setCity("Toronto");
		address.setProvince("ON");
		address.setCountry("Canada");
		paymentDTO.setAddress(address);
		PurchaseDTO purchase = new PurchaseDTO(); 
		purchase.setItem("Hat");
		purchase.setAmount(30.00);
		paymentDTO.setPurchase(purchase);
		PaymentDTO response= paymentServiceImpl.processPayment(paymentDTO);
		assertNotNull(response);
	}
	
	private ResponseEntity<String> restTempResponse() {
		String response = "status=201 & response=null";
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	private Payment save() {
		Payment payment = new Payment();
		payment.setPaymentId(1);
		payment.setFirstName("John");
		payment.setLastName("Doe");
		payment.setEmailAddress("john.doe@gmail.com");
		payment.setStreet("1234 Given Lane");
		payment.setCity("Toronto");
		payment.setProvince("ON");
		payment.setCountry("Canada");
		payment.setItem("Hat");
		payment.setAmount(30.00);
		return payment;
	}
}
