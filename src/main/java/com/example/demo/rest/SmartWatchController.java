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

import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartWatchService;

@Path("/smartwatch")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SmartWatchController {

	private SmartWatchService smartwatchService;

	public SmartWatchController(SmartWatchService smartwatchService) {
		this.smartwatchService = smartwatchService;
	}
	
    @GET
    public List<SmartWatch> findAll(){
        return smartwatchService.findAll();
    }

    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id){
    	SmartWatch phone = smartwatchService.findOne(id);
        if (phone == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(phone).build();
    }

    @POST
    public Response create(SmartWatch watch){
        if (watch.getId() != 0) // si ya tiene id quiere decir que es una actualización no creación
            return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(smartwatchService.save(watch)).build();
    }

    @PUT
    public Response update(SmartWatch watch){
        if (watch.getId() == null || watch.getId() == 0) // si no tiene id quiere decir que es una creación no actualización
            return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(smartwatchService.save(watch)).build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteOne(@PathParam("id") Long id){
        if(!smartwatchService.delete(id))
            return Response.ok(Response.Status.NOT_FOUND).build();
        return Response.ok(Response.Status.OK).build();
    }

    @DELETE
    public Response deleteAll(){
    	smartwatchService.deleteAll();
        return Response.ok(Response.Status.OK).build();
    }
}
