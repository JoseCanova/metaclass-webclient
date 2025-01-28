package org.nanotek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class WebclientApp {

	@Autowired
	WebClient webclient;
	
	public WebclientApp() {
	}
	public static void main(String[] args) {
		SpringApplication.run(WebclientApp.class, args);
	}
	
}
