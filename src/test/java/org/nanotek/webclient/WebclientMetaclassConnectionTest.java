package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.BaseUriConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.netty.http.client.HttpClientRequest;

@SpringBootTest
public class WebclientMetaclassConnectionTest {

	@Autowired
	WebClient webClient;
	
	@Autowired
	BaseUriConnection baseUriConnection;
	
	public WebclientMetaclassConnectionTest() {
	}
	
	boolean taskDone = false;

	@Test
	void webClientConnectionTest() {
		assertNotNull(webClient);
		JsonNode node = webClient.get()
		.uri("http://localhost:8086/meta-class")
		.accept(MediaType.APPLICATION_JSON)
		.httpRequest(httpRequest -> {
			HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
			reactorRequest.responseTimeout(Duration.ofSeconds(20));
		})
		.retrieve()
		.bodyToMono(JsonNode.class)
		.block();
		assertNotNull(node);
	}
	
}
