package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/bye")
public class ByeController {

	/**
	 * http://localhost:8080/api/bye/metodo1
	 */
	@GET
	@Path("/metodo1")
	public String metodo1() {
		return "Adios desde ByeController metodo1";
	}
}
