package com.dk.catalogclient;


import com.dk.catalogclient.response.FetchWorksResponse;
import com.google.inject.Inject;
import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HystrixCatalogClient implements CatalogClient {

    private Provider<FetchWorksCommand> fetchWorksCommandProvider;

    @Inject
    public HystrixCatalogClient(Provider<FetchWorksCommand> fetchWorksCommand) {
        this.fetchWorksCommandProvider = fetchWorksCommand;
    }

    public FetchWorksResponse fetchWorks() {
        log.info("Calling external API to fetch works");
        return fetchWorksCommandProvider
                .get()
                .execute();
    }
}
