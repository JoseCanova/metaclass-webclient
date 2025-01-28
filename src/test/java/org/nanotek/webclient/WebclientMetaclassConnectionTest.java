package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.BaseUriConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClientRequest;

@SpringBootTest
public class WebclientMetaclassConnectionTest {

	@Autowired
	WebClient webClient;
	
	@Autowired
	BaseUriConnection baseUriConnection;
	
	public WebclientMetaclassConnectionTest() {
	}

	@Test
	void webClientConnectionTest() {
		assertNotNull(webClient);
		webClient.get()
		.uri("http://locahost:8086/meta-class")
		.httpRequest(httpRequest -> {
			HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
			reactorRequest.responseTimeout(Duration.ofSeconds(20));
		})
		.retrieve()
		.bodyToMono(String.class);
	}
	
}
