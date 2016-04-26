package com.dk.digitalstore.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/works")
public class WorksResource {

    @Path("/search")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response search() {
        return Response.noContent().build();
    }

}
