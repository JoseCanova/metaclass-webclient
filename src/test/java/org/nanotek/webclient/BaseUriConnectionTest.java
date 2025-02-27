package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.BaseMetaClassUriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseUriConnectionTest {

	@Autowired
	BaseMetaClassUriService baseUriConnection;
	
	@Test
	public void testUriConnection() {
		assertNotNull(baseUriConnection);
		assertTrue(8086==baseUriConnection.getBasePort());
		assertNotNull(baseUriConnection.getBaseMetaClassPath());
		assertNotNull(baseUriConnection.buildBaseUri());
		assertTrue (baseUriConnection.buildBaseUri().getClass().equals(URI.class));
		System.out.println(baseUriConnection.buildBaseUri().toString());
	}

}
