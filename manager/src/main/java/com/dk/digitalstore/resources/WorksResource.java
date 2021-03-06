package com.dk.digitalstore.resources;

import com.codahale.metrics.annotation.Timed;
import com.dk.digitalstore.action.FetchMakesAction;
import com.dk.digitalstore.action.FetchModelsAction;
import com.dk.digitalstore.action.FetchWorksAction;
import com.dk.digitalstore.model.FiltersResponse;
import com.dk.digitalstore.model.GetAllWorksResponse;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/works")
@Api("/works")
public class WorksResource {

    @Inject
    private Provider<FetchWorksAction> fetchWorksActionProvider;

    @Inject
    private Provider<FetchMakesAction> filtersActionProvider;

    @Inject
    private Provider<FetchModelsAction> fetchModelsActionProvider;

    @Path("/search")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Search works by make or model or both. Default returns all works")
    public GetAllWorksResponse search(@QueryParam("make") String make, @QueryParam("model") String model) {
        return fetchWorksActionProvider
                .get()
                .withMake(make)
                .withModel(model)
                .invoke();
    }

    @Path("/makes")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Fetch all available makes")
    public FiltersResponse fetchMakes() {
        return filtersActionProvider
                .get()
                .invoke();

    }

    @Path("/models")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Fetch all models for a given make")
    public FiltersResponse fetchModelsForMake(@QueryParam("make") String make) {
        return fetchModelsActionProvider
                .get()
                .withInput(make)
                .invoke();

    }

}
