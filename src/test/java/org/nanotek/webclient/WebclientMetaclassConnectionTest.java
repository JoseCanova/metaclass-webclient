package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.metaclass.webclient.BaseUriConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
	
	boolean taskDone = false;

	@Test
	void webClientConnectionTest() {
		assertNotNull(webClient);
		assertNotNull(baseUriConnection);
		
		List<?> node = webClient.get()
		.uri(baseUriConnection.buildUri())
		.accept(MediaType.APPLICATION_JSON)
		.httpRequest(httpRequest -> {
			HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
			reactorRequest.responseTimeout(Duration.ofSeconds(20));
		})
		.retrieve()
		.bodyToMono(List.class)
		.block();
		assertNotNull(node);
		
	}
	
}
