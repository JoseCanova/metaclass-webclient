package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.BaseUriConnection;
import org.nanotek.metaclass.webclient.config.WebclientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseUriConnectionTest {

	@Autowired
	BaseUriConnection baseUriConnection;
	
	@Test
	public void testUriConnection() {
		assertNotNull(baseUriConnection);
		assertTrue("http://localhost/meta-class".equals(baseUriConnection.getBaseUri()));
		assertTrue(Integer.valueOf(8086).equals(baseUriConnection.getBasePort()));
	}

}
