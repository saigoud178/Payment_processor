package com.intuit.craft.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class CommonConfiguration {

	@Value("${payment.transaction.api.url}")
	private String paymentApiUrl;

	@Value("${payment.transaction.access.tocken}")
	private String accessToken;

	@Value("${remote.connection.read.time.out}")
	private int readTimeOut;

	@Value("${remote.connection.socket.time.out}")
	private int connectionTimeOut;
	
}
