package org.nanotek.metaclass.webclient.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import java.time.Duration;
import java.util.function.Function;

import org.nanotek.metaclass.webclient.controller.MetaClassServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.netty.http.client.HttpClient;

@SpringBootConfiguration
@ComponentScan(basePackages = {"org.nanotek.metaclass.webclient" })
public class WebclientConfiguration {

	@SuppressWarnings("deprecation")
	@Bean
	public ReactorResourceFactory resourceFactory() {
		ReactorResourceFactory factory = new ReactorResourceFactory();
		factory.setUseGlobalResources(false); 
		return factory;
	}

	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public WebClient webClient() {

		Function<HttpClient, HttpClient> mapper = client -> {
			client.responseTimeout(Duration.ofMillis(90000L));
			return client;
		};

		ClientHttpConnector connector =
				new ReactorClientHttpConnector(resourceFactory(), mapper); 

		return WebClient.builder()
				.clientConnector(connector).build(); 
	}
	
	 @Bean
	  public RouterFunction<ServerResponse> routeMetaClassList(@Autowired MetaClassServiceHandler handler) {

	    return RouterFunctions
	    		.route(GET("/meta-class").and(accept(MediaType.APPLICATION_JSON)), handler::getMetaClassList);
	 }
	 
}
