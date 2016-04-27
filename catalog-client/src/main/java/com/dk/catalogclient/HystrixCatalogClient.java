package com.dk.catalogclient;


import com.dk.catalogclient.response.FetchWorksResponse;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HystrixCatalogClient implements CatalogClient {

    private Provider<FetchWorksCommand> fetchWorksCommandProvider;

    @Inject
    public HystrixCatalogClient(Provider<FetchWorksCommand> fetchWorksCommand) {
        this.fetchWorksCommandProvider = fetchWorksCommand;
    }

    public FetchWorksResponse fetchWorks() {
        return fetchWorksCommandProvider
                .get()
                .execute();
    }
}
