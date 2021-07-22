package com.example.demo.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.example.demo.domain.SmartPhone;
import com.example.demo.service.SmartPhoneService;

@Path("/smartphone")
@Component
@Produces(MediaType.APPLICATION_JSON) // indica que devuelve o produce JSON
@Consumes(MediaType.APPLICATION_JSON) // indica que recibe o consume JSON
public class SmartPhoneController {

	private SmartPhoneService smartphoneService;

	public SmartPhoneController(SmartPhoneService smartphoneService) {
		this.smartphoneService = smartphoneService;
	}
	
	/**
	 * GET http://localhost:8080/api/smartphone
	 */
    @GET
    public List<SmartPhone> findAll(){
        return smartphoneService.findAll();
    }

    /**
     * GET
     * http://localhost:8080/api/smartphone/1
     * http://localhost:8080/api/smartphone/2
     * http://localhost:8080/api/smartphone/3
     * http://localhost:8080/api/smartphone/4
     */
    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id){
    	SmartPhone phone = smartphoneService.findOne(id);
        if (phone == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(phone).build();
    }

    /**
     * POST http://localhost:8080/api/smartphone
     */
    @POST // CREAR NUEVO
    public Response create(SmartPhone phone){
        if (phone.getId() != 0) // si ya tiene id quiere decir que es una actualización no creación
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        SmartPhone result = smartphoneService.save(phone);
        return Response.ok(result).build();
    }

    /**
     * PUT http://localhost:8080/api/smartphone
     */
    @PUT // ACTUALIZAR
    public Response update(SmartPhone phone){
        if (phone.getId() == null || phone.getId() == 0) // si no tiene id quiere decir que es una creación no actualización
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        SmartPhone result = smartphoneService.save(phone);
        return Response.ok(result).build();
    }


    /**
     * DELETE
     * http://localhost:8080/api/smartphone/1
     * http://localhost:8080/api/smartphone/2
     */
    @DELETE
    @Path("{id}")
    public Response deleteOne(@PathParam("id") Long id){
        if(!smartphoneService.delete(id))
        	return Response.status(Response.Status.NOT_FOUND).build();
        
        return Response.ok(Response.Status.OK).build();
    }

    /**
     * DELETE
     * http://localhost:8080/api/smartphone
     */
    @DELETE
    public Response deleteAll(){
    	smartphoneService.deleteAll();
        return Response.ok(Response.Status.OK).build();
    }
	
	
}
