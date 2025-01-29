package org.nanotek.metaclass.webclient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Validator;
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
	
	public List<RdbmsMetaClass> retrieveMetaClasses(){
		var theList = new ArrayList<RdbmsMetaClass>();
		webClient.get()
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
				.subscribe(l -> convertAndPopulateList(l , theList));
		return theList;
	}


	private void convertAndPopulateList(List<?> l, List<RdbmsMetaClass> arrayList) {
		l.stream()
		.map(jsonNode -> objectMapper.convertValue(jsonNode, RdbmsMetaClass.class))
		.forEach(mc -> arrayList.add(mc));
	}
	
	
}
