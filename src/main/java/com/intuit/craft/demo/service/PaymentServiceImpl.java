package com.intuit.craft.demo.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.intuit.craft.demo.configuration.CommonConfiguration;
import com.intuit.craft.demo.model.Payment;
import com.intuit.craft.demo.model.dto.PaymentDTO;
import com.intuit.craft.demo.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonConfiguration commonConfiguration;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public PaymentDTO processPayment(PaymentDTO paymentDTO) {
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentDTO, payment);
		payment.setStreet(paymentDTO.getAddress().getStreet());
		payment.setCity(paymentDTO.getAddress().getCity());
		payment.setProvince(paymentDTO.getAddress().getProvince());
		payment.setCountry(paymentDTO.getAddress().getCountry());
		payment.setItem(paymentDTO.getPurchase().getItem());
		payment.setAmount(paymentDTO.getPurchase().getAmount());
		ResponseEntity<String> response = null;
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			StringBuilder sb = new StringBuilder();
			sb.append("Bearer ");
			sb.append(commonConfiguration.getAccessToken());
			httpHeaders.set("Authorization", sb.toString());
			String requestBody = getPaymentApiRequest(payment);

			HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
			response = restTemplate.postForEntity(commonConfiguration.getPaymentApiUrl(), httpEntity, String.class);
			if (response.hasBody() || response.getBody() != null) {
				logger.info(response.getBody());
			}
		} catch (Exception e) {
			logger.error("Error occured while calling create payment api", e.getMessage());
		}

		payment = paymentRepository.save(payment);
		BeanUtils.copyProperties(payment, paymentDTO);

		paymentDTO.getAddress().setStreet(payment.getStreet());
		paymentDTO.getAddress().setCity(payment.getCity());
		paymentDTO.getAddress().setProvince(payment.getProvince());
		paymentDTO.getAddress().setCountry(payment.getCountry());
		paymentDTO.getPurchase().setItem(payment.getItem());
		paymentDTO.getPurchase().setAmount(payment.getAmount());

		return paymentDTO;
	}

	private String getPaymentApiRequest(Payment payment) {

		String request = "{\n" + "  \"intent\": \"sale\",\n" + "  \"payer\": {\n"
				+ "    \"payment_method\": \"paypal\"\n" + "  },\n" + "  \"transactions\": [{\n" + "    \"amount\": {\n"
				+ "      \"total\": \"30.11\",\n" + "      \"currency\": \"USD\",\n" + "      \"details\": {\n"
				+ "        \"subtotal\": \"" + payment.getAmount() + "\",\n" + "        \"tax\": \"0.07\",\n"
				+ "        \"shipping\": \"0.03\",\n" + "        \"handling_fee\": \"1.00\",\n"
				+ "        \"shipping_discount\": \"-1.00\",\n" + "        \"insurance\": \"0.01\"\n" + "      }\n"
				+ "    },\n" + "    \"description\": \"This is the payment transaction description.\",\n"
				+ "    \"custom\": \"EBAY_EMS_90048630024435\",\n" + "    \"invoice_number\": \"48787589673\",\n"
				+ "    \"payment_options\": {\n" + "      \"allowed_payment_method\": \"INSTANT_FUNDING_SOURCE\"\n"
				+ "    },\n" + "    \"soft_descriptor\": \"ECHI5786786\",\n" + "    \"item_list\": {\n"
				+ "      \"items\": [{\n" + "        \"name\": \"hat\",\n"
				+ "        \"description\": \"Brown color hat\",\n" + "        \"quantity\": \"5\",\n"
				+ "        \"price\": \"3\",\n" + "        \"tax\": \"0.01\",\n" + "        \"sku\": \"1\",\n"
				+ "        \"currency\": \"USD\"\n" + "      }, {\n" + "        \"name\": \"handbag\",\n"
				+ "        \"description\": \"Black color hand bag\",\n" + "        \"quantity\": \"1\",\n"
				+ "        \"price\": \"15\",\n" + "        \"tax\": \"0.02\",\n" + "        \"sku\": \"product34\",\n"
				+ "        \"currency\": \"USD\"\n" + "      }],\n" + "      \"shipping_address\": {\n"
				+ "        \"recipient_name\": \"" + payment.getFirstName() + " " + payment.getLastName() + "\",\n"
				+ "        \"line1\": \"" + payment.getStreet() + "\",\n" + "        \"line2\": \""
				+ payment.getProvince() + "\",\n" + "        \"city\": \"" + payment.getCity() + "\",\n"
				+ "        \"country_code\": \"US\",\n" + "        \"postal_code\": \"95131\",\n"
				+ "        \"phone\": \"011862212345678\",\n" + "        \"state\": \"CA\"\n" + "      }\n" + "    }\n"
				+ "  }],\n" + "  \"note_to_payer\": \"Contact us for any questions on your order.\",\n"
				+ "  \"redirect_urls\": {\n" + "    \"return_url\": \"https://example.com\",\n"
				+ "    \"cancel_url\": \"https://example.com\"\n" + "  }\n" + "}";
		return request;
	}
}
