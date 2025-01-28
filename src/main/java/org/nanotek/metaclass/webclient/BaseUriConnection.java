package org.nanotek.metaclass.webclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix="webclient-connection")
@ConfigurationPropertiesScan
public class BaseUriConnection{
	
	private String baseUri;
	
	private Integer basePort;
	
	public BaseUriConnection() {
		super();
	}
			
	public BaseUriConnection(String baseUri, Integer basePort) {
		super();
		this.baseUri = baseUri;
		this.basePort = basePort;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public Integer getBasePort() {
		return basePort;
	}

	public void setBasePort(Integer basePort) {
		this.basePort = basePort;
	}
	
	
}
