package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.dk.digitalstore.internal.FetchWorksFactory;
import com.dk.digitalstore.model.GetAllWorksResponse;
import com.google.inject.Inject;
import org.dozer.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FetchWorksAction implements Action<GetAllWorksResponse> {

    private final CatalogClient catalogClient;

    private String make;

    private String model;

    private Mapper mapper;

    @Inject
    public FetchWorksAction(CatalogClient catalogClient, Mapper mapper) {
        this.catalogClient = catalogClient;
        this.mapper = mapper;
    }


    public GetAllWorksResponse invoke() {
        FetchWorksResponse response = fetchWorks();
        Stream<FetchWorksResponse.Work> workStream = response.getWork().stream();
        List<FetchWorksResponse.Work> result;
        if (make != null) {
            if (model != null) {
                result = workStream
                        .filter(w -> w.getExif().getMake() != null && w.getExif().getModel() != null)
                        .filter(w -> w.getExif().getMake().equals(make) && w.getExif().getModel().equals(model))
                        .collect(Collectors.toList());
            } else {
                result = workStream
                        .filter(w -> w.getExif().getMake() != null)
                        .filter(w -> w.getExif().getMake().equals(make)).
                                collect(Collectors.toList());
            }
        } else if (model != null) {
            result = workStream
                    .filter(w -> w.getExif().getModel() != null)
                    .filter(w -> w.getExif().getModel().equals(model))
                    .collect(Collectors.toList());
        } else {
            result = response.getWork();
        }
        return transform(result);

    }

    private GetAllWorksResponse transform(List<FetchWorksResponse.Work> result) {
        GetAllWorksResponse getAllWorksResponse = new GetAllWorksResponse();
        for (FetchWorksResponse.Work work : result) {
            getAllWorksResponse.addWork(mapper.map(work, GetAllWorksResponse.Work.class));
        }
        return getAllWorksResponse;
    }

    private FetchWorksResponse fetchWorks() {
        if (FetchWorksFactory.fetchWorks() == null) {
            FetchWorksFactory.setResponse(catalogClient.fetchWorks());
        }
        return FetchWorksFactory.fetchWorks();
    }

    public FetchWorksAction withMake(String make) {
        this.make = make;
        return this;
    }

    public FetchWorksAction withModel(String model) {
        this.model = model;
        return this;
    }
}
