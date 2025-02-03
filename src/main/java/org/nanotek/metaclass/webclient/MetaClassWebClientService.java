package org.nanotek.metaclass.webclient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Validator;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;


//TODO: insert validation and correct pattern for mono-clients.
//TODO: clean up "json model" from entity-related anotattions
@Service
public class MetaClassWebClientService {

	@Autowired 
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private BaseMetaClassUriService baseMetaClassUriService;
	
	@Autowired
	Validator validator;
	
	
	private Set<?> validateMetaClass(RdbmsMetaClass mc) {
		return validator.validate(mc);
	}
	
	
	public MetaClassWebClientService() {
	}
	
	public Mono<List> retrieveMetaClasses(){
		return webClient.get()
				.uri(baseMetaClassUriService.buildBaseUri()
						.toString()
						.concat("/")
						.concat (baseMetaClassUriService.getBaseMetaClassPath()))
				.accept(MediaType.APPLICATION_JSON)
				.httpRequest(httpRequest -> {
					HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
					reactorRequest.responseTimeout(Duration.ofSeconds(20));
				})
				.retrieve()
				.bodyToMono(List.class)
				.flatMap(l -> Mono.justOrEmpty(l));
	}


	private  List<RdbmsMetaClass> convertAndPopulateList(List<?> l) {
		return l.stream()
		.map(jsonNode -> objectMapper.convertValue(jsonNode, RdbmsMetaClass.class))
		.collect(Collectors.toList());
	}
	
	
}
