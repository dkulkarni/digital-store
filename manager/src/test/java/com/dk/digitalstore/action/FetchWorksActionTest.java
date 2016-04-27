package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.digitalstore.internal.FetchWorksFactory;
import com.dk.digitalstore.model.GetAllWorksResponse;
import com.google.inject.Inject;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;


public class FetchWorksActionTest {

    private FetchWorksAction fetchWorksAction;

    @Inject
    private Mapper mapper;

    @Mock
    private CatalogClient catalogClient;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        fetchWorksAction = new FetchWorksAction(catalogClient, mapper);
    }

    @Test
    public void shouldReturnAllWorks() throws Exception {
        Mockito.when(catalogClient.fetchWorks()).thenReturn(FetchWorksFactory.fetchWorks());
        GetAllWorksResponse getAllWorksResponse = fetchWorksAction
                .invoke();
        assertEquals(3, getAllWorksResponse.getWork().size());
    }

    @Test
    public void shouldReturnNikonWorks() throws Exception {
        Mockito.when(catalogClient.fetchWorks()).thenReturn(FetchWorksFactory.fetchWorks());
        GetAllWorksResponse getAllWorksResponse = fetchWorksAction
                .withMake("NIKON")
                .invoke();
        assertEquals(2, getAllWorksResponse.getWork().size());
    }


}