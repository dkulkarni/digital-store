package com.dk.digitalstore.resources;

import com.codahale.metrics.annotation.Timed;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.dk.digitalstore.action.FetchWorksAction;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/works")
public class WorksResource {

    @Inject
    private Provider<FetchWorksAction> fetchWorksActionProvider;

    @Path("/search")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public FetchWorksResponse search() {
        return fetchWorksActionProvider
                .get()
                .invoke();
    }

}
