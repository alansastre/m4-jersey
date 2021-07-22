package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 * Este controlador responde ante la URL:
 * 
 * 
 * @Path("/hello") http://localhost:8080/api/hello/metodo1
 * http://localhost:8080/api/hello/metodo2
 * http://localhost:8080/api/hello/metodo3/texto
 * 
 * @Path("") http://localhost:8080/api/metodo1 http://localhost:8080/api/metodo1
 * 
 * Métodos HTTP:
 * 
 * GET POST PUT DELETE
 */
@Path("/hello") // enrutado
// @Path("") // enrutado
@Component
public class HelloController {

	/**
	 * http://localhost:8080/api/hello
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String metodo1() {
		return "Hola desde método 1";
	}

	/**
	 * http://localhost:8080/api/hello/metodo2
	 */
	@GET
	@Path("/metodo2")
	@Produces(MediaType.TEXT_PLAIN)
	public String metodo2() {
		return "Hola desde método 2";
	}

	/**
	 * http://localhost:8080/api/hello/metodo3/texto
	 */
	@GET
	@Path("/metodo3/texto")
	@Produces(MediaType.TEXT_PLAIN)
	public String metodo3() {
		return "Hola desde método 3";
	}

	/**
	 * http://localhost:8080/api/hello/html
	 */
	@GET
	@Path("/html")
	@Produces(MediaType.TEXT_HTML)
	public String html() {
		return """
				<!DOCTYPE html>
				<html>
				<head>
				<meta charset="ISO-8859-1">
				<title>Insert title here</title>
				</head>
				<body>
				<h1 style="color:red;">Desarrollos cosmicos!!</h1>
				</body>
				</html>
				""";
	}

}
