package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.digitalstore.model.FiltersResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class FetchMakesActionTest {

    private FetchMakesAction fetchMakesAction;

    @Mock
    private CatalogClient catalogClient;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        fetchMakesAction = new FetchMakesAction(catalogClient);
    }

    @Test
    public void shouldFetchAllMakes() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        FiltersResponse filtersResponse = fetchMakesAction
                .invoke();
        assertEquals(2, filtersResponse.getFilters().size());
        assertTrue(filtersResponse.getFilters().contains("NIKON"));
        assertTrue(filtersResponse.getFilters().contains("CANON"));
    }

}