package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.example.demo.service.SmartDeviceFactory;

/**
 * CRUD
 * 
 * RETRIEVE ONE id RETRIEVE ALL
 */
@Component
@Path("/smartdevice")
@Produces(MediaType.APPLICATION_JSON)
public class SmartDeviceController {

	/**
	 * http://localhost:8080/api/smartdevice
	 * Metodo para comprobar configuracion 
	 * @return
	 */
	@GET
	public Response hello() {
		String mensaje = "Hola desde SmartDeviceController!";
		return Response.ok().entity(mensaje).build();
	}

	/**
	 * http://localhost:8080/api/smartdevice/phone
	 * http://localhost:8080/api/smartdevice/watch
	 * http://localhost:8080/api/smartdevice/dishwasher (NO EXISTE, DARÁ 404 NOT
	 * FOUND)
	 * Metodo para pruebas con los patrones factory-facade
	 * 
	 * @param type
	 * @return
	 */
	@GET
	@Path("/{type}")
	public Response createByType(@PathParam("type") String type) {
		System.out.println("SmartDeviceController executing createByType.");
		try {
			return Response
					.ok()
					.entity(SmartDeviceFactory.createByType(type))
					.build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

}
