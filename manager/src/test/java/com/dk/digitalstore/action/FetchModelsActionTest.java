package com.dk.digitalstore.action;

import com.dk.catalogclient.CatalogClient;
import com.dk.digitalstore.exception.DigitalStoreException;
import com.dk.digitalstore.model.FiltersResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class FetchModelsActionTest {

    private FetchModelsAction fetchModelsAction;

    @Mock
    private CatalogClient catalogClient;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        fetchModelsAction = new FetchModelsAction(catalogClient);
    }

    @Test
    public void shouldFetchAllMakes() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        FiltersResponse filtersResponse = fetchModelsAction
                .withInput("NIKON")
                .invoke();
        assertEquals(2, filtersResponse.getFilters().size());
        assertTrue(filtersResponse.getFilters().contains("NIKON CORPORATION"));
        assertTrue(filtersResponse.getFilters().contains("NIKON CORPORATION23"));
    }

    @Test(expected = DigitalStoreException.class)
    public void shouldFailIfMakeIsNull() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        fetchModelsAction
                .invoke();
    }

    @Test
    public void shouldReturnEmptyResponseWithNoMatches() throws Exception {
        when(catalogClient.fetchWorks()).thenReturn(WorksFactory.fetchAllWorks());
        FiltersResponse filtersResponse = fetchModelsAction
                .withInput("randomMake")
                .invoke();
        assertEquals(0, filtersResponse.getFilters().size());
    }

}