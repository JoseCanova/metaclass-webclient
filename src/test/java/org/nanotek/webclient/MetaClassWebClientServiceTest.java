package org.nanotek.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.nanotek.metaclass.webclient.MetaClassWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MetaClassWebClientServiceTest {

	
	@Autowired
	MetaClassWebClientService metaClassWebClientService;
	
	
	public MetaClassWebClientServiceTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	void testWebClientMetaClassService() throws InterruptedException {
		assertNotNull(metaClassWebClientService);
//		List<?> theList = metaClassWebClientService.retrieveMetaClasses();
//		assertNotNull(theList);
//		Thread.sleep(Duration.ofSeconds(10));
	}
}
