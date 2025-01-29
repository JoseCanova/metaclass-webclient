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
public class BaseMetaClassUriService{
	
	private String baseMetaClassPath;
	
	private String scheme;
	
	private String baseHost;

	private Integer basePort;

	private UriComponentsBuilder uriComponentsBuilder;

	public BaseMetaClassUriService() {
		super();
	}
			
	@PostConstruct
	public void postConstruct() {
		uriComponentsBuilder =  UriComponentsBuilder.newInstance();
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
	


	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}

	public String getBaseMetaClassPath() {
		return baseMetaClassPath;
	}

	public void setBaseMetaClassPath(String baseMetaClassPath) {
		this.baseMetaClassPath = baseMetaClassPath;
	}
	
	public URI buildBaseUri() {
		return uriComponentsBuilder.scheme(scheme).host(baseHost).port(basePort).build().toUri();
	}
	
	public String buildMetaClassUriString() {
		return buildBaseUri().toString().concat("/").concat(baseMetaClassPath);
	}

}
