package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.google.inject.Inject;

/**
 * Created by dkulkarni on 26/04/16.
 */
public class FetchWorksAction implements Action<FetchWorksResponse> {

    private final CatalogClient catalogClient;

    @Inject
    public FetchWorksAction(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }


    public FetchWorksResponse invoke() {
        return catalogClient.fetchWorks();
    }
}
