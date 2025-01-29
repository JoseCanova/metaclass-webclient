package org.nanotek.metaclass.webclient;

import java.util.List;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MetaClassWebClientService {

	@Autowired 
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private BaseMetaClassUriService baseMetaClassUriService;
	
	public MetaClassWebClientService() {
	}
	
	public List<RdbmsMetaClass> retrieveMetaClasses(){
		return null;
	}
	
	
}
