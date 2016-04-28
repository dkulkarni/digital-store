package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.dk.digitalstore.internal.FetchWorksFactory;
import com.dk.digitalstore.model.FiltersResponse;
import com.google.inject.Inject;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FetchMakesAction implements Action<FiltersResponse> {

    private final CatalogClient catalogClient;

    @Inject
    public FetchMakesAction(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public FiltersResponse invoke() {
        FetchWorksResponse response = fetchWorks();
        Stream<FetchWorksResponse.Work> workStream = response.getWork().stream();
        FiltersResponse filtersResponse = new FiltersResponse();
        filtersResponse.setFilters(
                workStream
                        .filter(w -> w.getExif().getMake() != null)
                        .map(w -> w.getExif().getMake())
                        .collect(Collectors.toSet())
        );
        return filtersResponse;

    }

    private FetchWorksResponse fetchWorks() {
        if (FetchWorksFactory.fetchWorks() == null) {
            FetchWorksFactory.setResponse(catalogClient.fetchWorks());
        }
        return FetchWorksFactory.fetchWorks();
    }
}
