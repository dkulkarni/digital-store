package com.dk.catalogclient;


import com.dk.catalogclient.config.CatalogConfiguration;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.google.inject.Inject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class FetchWorksCommand extends BaseCatalogCommand<FetchWorksResponse> {

    private static final String FETCH_WORKS_URL = "/works.xml";

    private final CatalogConfiguration catalogConfig;

    @Inject
    public FetchWorksCommand(Client client, CatalogConfiguration catalogConfig) {
        super(client);
        this.catalogConfig = catalogConfig;
    }

    @Override
    protected FetchWorksResponse run() throws Exception {
        ClientResponse response = client
                .resource(buildUri())
                .accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_XML_TYPE)
                .get(ClientResponse.class);
        handleErrors(response);
        return response.getEntity(FetchWorksResponse.class);
    }

    private URI buildUri() {
        return UriBuilder
                .fromUri(catalogConfig.getUrl())
                .path(FETCH_WORKS_URL)
                .build();
    }
}
