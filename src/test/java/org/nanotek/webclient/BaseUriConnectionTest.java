package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.BaseUriConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseUriConnectionTest {

	@Autowired
	BaseUriConnection baseUriConnection;
	
	@Test
	public void testUriConnection() {
		assertNotNull(baseUriConnection);
		assertTrue(8086==baseUriConnection.getBasePort());
		assertNotNull(baseUriConnection.getBasePath());
		assertNotNull(baseUriConnection.buildUri());
		assertTrue (baseUriConnection.buildUri().getClass().equals(URI.class));
		System.out.println(baseUriConnection.buildUri().toString());
	}

}
