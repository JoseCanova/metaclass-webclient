package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.metaclass.webclient.BaseMetaClassUriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Validator;
import reactor.netty.http.client.HttpClientRequest;

@SpringBootTest
public class WebclientMetaclassConnectionTest {

	@Autowired
	WebClient webClient;
	
	@Autowired
	BaseMetaClassUriService baseUriConnection;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Validator validator;
	
	public WebclientMetaclassConnectionTest() {
	}
	
	boolean taskDone = false;

	@Test
	@Order(1)
	void webClientConnectionTest() {
		assertNotNull(webClient);
		assertNotNull(baseUriConnection);
		
		List<?> node = webClient.get()
		.uri(baseUriConnection.buildBaseUri()
				.toString()
				.concat("/")
				.concat (baseUriConnection.getBaseMetaClassPath()))
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
	
	@Test
	@Order(2)
	public void testMonoWebClientObjectMapper() {
		assertNotNull(validator);
		assertNotNull(webClient);
		assertNotNull(baseUriConnection);
		
		List<?> nodes = webClient.get()
		.uri(baseUriConnection.buildBaseUri()
				.toString()
				.concat("/")
				.concat (baseUriConnection.getBaseMetaClassPath()))
		.accept(MediaType.APPLICATION_JSON)
		.httpRequest(httpRequest -> {
			HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
			reactorRequest.responseTimeout(Duration.ofSeconds(20));
		})
		.retrieve()
		.bodyToMono(List.class)
		.block();
		assertNotNull(nodes);
		assertNotNull(objectMapper);

		List<RdbmsMetaClass> mcs = nodes
		.stream()
		.map(mc -> objectMapper.convertValue(mc , RdbmsMetaClass.class))
		.collect(Collectors.toList());
		
		Long validationNum = mcs
		.stream()
		.map(mc -> {assertNotNull(mc);return mc;})
		.map(mc -> validateMetaClass(mc))
		.filter(s -> !s.isEmpty())
		.count();
		
		assertTrue(validationNum == 0);
	}

	private Set<?> validateMetaClass(RdbmsMetaClass mc) {
		return validator.validate(mc);
	}
	
	
}
