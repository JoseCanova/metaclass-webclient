package org.nanotek.metaclass.webclient.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.metaclass.webclient.MetaClassWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class MetaClassServiceHandler {

	@Autowired 
	MetaClassWebClientService metaClassWebClientService; 

	@Autowired
	ObjectMapper objectMapper;
	
	public MetaClassServiceHandler() {
	}

	public Mono<ServerResponse> getMetaClassList(ServerRequest request) {
		return metaClassWebClientService
		.retrieveMetaClasses()
		.map(l-> mapClasses(l))
		.flatMap(l -> ServerResponse.ok().bodyValue(l))
		.doOnTerminate(()->System.out.println("Custer"));
	}

	private List mapClasses(List<?> l) {
		// TODO Auto-generated method stub
		return  l
				.stream()
				.map(v -> objectMapper.convertValue(v , RdbmsMetaClass.class))
				.collect(Collectors.toList());
	}

}
