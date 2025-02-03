package org.nanotek.metaclass.webclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.nanotek.metaclass.webclient.MetaClassWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class MetaClassServiceHandler {

	@Autowired 
	MetaClassWebClientService metaClassWebClientService; 

	public MetaClassServiceHandler() {
	}

	public Mono<ServerResponse> getMetaClassList(ServerRequest request) {
		return metaClassWebClientService
		.retrieveMetaClasses()
		.flatMap(l -> ServerResponse.ok().bodyValue(l))
		.doOnTerminate(()->System.out.println("kust"));
	}

}
