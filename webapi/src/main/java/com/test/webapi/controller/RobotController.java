package com.test.webapi.controller;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.test.webapi.model.Direction;
import com.test.webapi.model.Position;

@Path("/robot")
public class RobotController {
	
	@Context UriInfo uriInfo;

	@POST
	@Path("/{name}")
	public Response create(@PathParam("name") String name){
		//return 303 if robot already exists
		return Response.status(200).entity("Robot Created - "+name).build();
	}	

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] list(){
		String[] robots = {"Test1","Test2"};
		return robots;
	}	
	
	@POST
	@Path("/{name}/position")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response place(Position position){
		//return if robot does not exist
		return Response.status(200).entity("Robot Placed at "+position).build();
	}	
	
	@PUT
	@Path("/{name}/position/{command}")
	public Response change(@PathParam("command") String command){
		System.out.println("command:"+command);
		URI uri =  uriInfo.getAbsolutePath();
		Response r;
		r = Response.created(uri).build();
		return r;
	}
	
	@GET
	@Path("/{name}/position")
	@Produces(MediaType.APPLICATION_JSON)
	public Position report(@PathParam("name") String name){
		return new Position(1, 2, Direction.valueOf("EAST"));
	}
	
}
