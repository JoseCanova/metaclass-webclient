package org.nanotek.metaclass.webclient;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.annotation.PostConstruct;

@Service
@ConfigurationProperties(prefix="webclient-connection")
@ConfigurationPropertiesScan
public class BaseUriConnection{
	
	private String basePath;
	
	private String scheme;
	
	private String baseHost;

	private Integer basePort;

	private UriComponentsBuilder uriComponentsBuilder;

	public BaseUriConnection() {
		super();
	}
			
	@PostConstruct
	public void postConstruct() {
		uriComponentsBuilder =  UriComponentsBuilder.newInstance();
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	} 
	
	public Integer getBasePort() {
		return basePort;
	}

	public void setBasePort(Integer basePort) {
		this.basePort = basePort;
	}
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public URI buildUri() {
		return uriComponentsBuilder.scheme(scheme).host(baseHost).port(basePort).pathSegment(basePath).build().toUri();
	}

	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}


}
