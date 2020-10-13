package com.intuit.craft.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Configuration
@Component
public class PaymentConfiguration {

	@Autowired
	private CommonConfiguration commonConfiguration;	

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		rf.setConnectTimeout(commonConfiguration.getConnectionTimeOut());
		rf.setReadTimeout(commonConfiguration.getReadTimeOut());
		return restTemplate;
	}
}
