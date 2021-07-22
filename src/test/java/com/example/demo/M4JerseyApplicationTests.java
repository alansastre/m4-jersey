package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.SmartPhone;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class M4JerseyApplicationTests {

	private TestRestTemplate testRestTemplate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
		testRestTemplate = new TestRestTemplate(restTemplateBuilder);
	}

	@Test
	@DisplayName("Pruebas GET Hello Controller")
	void testGetHelloWorld() {

		// ejecutar el metodo de controlador a testear
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/api/hello", String.class);


		/*
		 * Recibimos una respuesta HTTP:
		 * 1. status
		 * 2. body
		 * 3. headers
		 */
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("Hola desde método 1", entity.getBody());

	}
	
	
	@Test
	void testFindAll() throws Exception {
		ResponseEntity<SmartPhone[]> entity = 
				this.testRestTemplate.getForEntity("/api/smartphone", SmartPhone[].class);
		
		// comprobaciones
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		
		System.out.println(entity.getBody());
		
		List<SmartPhone> phones = Arrays.asList(entity.getBody());
		for (SmartPhone smartPhone : phones) {
			System.out.println(smartPhone);
		}

	}
	@Test
	void testFindOne() {
		// preparacion
		
		// ejecucion
		ResponseEntity<SmartPhone> response = 
				this.testRestTemplate.getForEntity("/api/smartphone/1", SmartPhone.class);
		
		// comprobaciones
		assertEquals(HttpStatus.OK, response.getStatusCode());

		SmartPhone phone = response.getBody();
		assertEquals(1L , phone.getId());
	}
	

	@Test
	void testPost() {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String productPayload = """
								{
				    "id": 0,
				    "name": "Another smartphone otra vez prueba",
				    "ram": {
				        "id": 4,
				        "type": "DDR6",
				        "gigabytes": 32
				    },
				    "battery": {
				        "id": 3,
				        "capacity": 9500.0
				    },
				    "cpu": {
				        "id": 4,
				        "on": false,
				        "cores": 16
				    },
				    "wifi": true,
				    "camera": {
				        "id": 3,
				        "model": "front camera",
				        "megapixels": 58.5
				    }
				}
								""";

		final HttpEntity<String> request = new HttpEntity<>(productPayload, headers);

		final ResponseEntity<SmartPhone> response = 
				testRestTemplate.exchange("/api/smartphone", HttpMethod.POST, request, SmartPhone.class);

		// Comprobaciones: 
		// status
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// body
		SmartPhone smartphone = response.getBody();
		System.out.println(smartphone);
		assertEquals(4, smartphone.getId());
		assertNotNull(smartphone.getBattery());
		

	}
}
