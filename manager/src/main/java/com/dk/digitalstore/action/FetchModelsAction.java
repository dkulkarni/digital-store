package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.dk.digitalstore.exception.DigitalStoreException;
import com.dk.digitalstore.exception.ErrorCode;
import com.dk.digitalstore.internal.FetchWorksFactory;
import com.dk.digitalstore.model.FiltersResponse;
import com.google.inject.Inject;

import java.util.stream.Collectors;

public class FetchModelsAction implements Action<FiltersResponse> {


    private final CatalogClient catalogClient;

    private String make;

    @Inject
    public FetchModelsAction(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    public FetchModelsAction withInput(String make) {
        this.make = make;
        return this;
    }

    @Override
    public FiltersResponse invoke() {
        if (make == null) {
            throw new DigitalStoreException(ErrorCode.INVALID_INPUT, "Make cannot be null");
        }

        FetchWorksResponse response = fetchWorks();
        FiltersResponse filtersResponse = new FiltersResponse();
        filtersResponse.setFilters(
                response
                        .getWork()
                        .stream()
                        .filter(w -> w.getExif().getModel() != null)
                        .filter(w -> w.getExif().getMake().toLowerCase().equals(make.toLowerCase()))
                        .map(w -> w.getExif().getModel())
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
