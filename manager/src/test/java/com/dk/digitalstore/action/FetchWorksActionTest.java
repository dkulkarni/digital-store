package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.digitalstore.model.GetAllWorksResponse;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class FetchWorksActionTest {

    private FetchWorksAction fetchWorksAction;

    private Mapper mapper = new DozerBeanMapper();

    @Mock
    private CatalogClient catalogClient;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        fetchWorksAction = new FetchWorksAction(catalogClient, mapper);
    }

    @Test
    public void shouldReturnAllWorks() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        GetAllWorksResponse getAllWorksResponse = fetchWorksAction
                .invoke();
        assertEquals(3, getAllWorksResponse.getWork().size());
    }

    @Test
    public void shouldReturnNikonWorks() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        GetAllWorksResponse getAllWorksResponse = fetchWorksAction
                .withMake("NIKON")
                .invoke();
        assertEquals(2, getAllWorksResponse.getWork().size());
    }


}