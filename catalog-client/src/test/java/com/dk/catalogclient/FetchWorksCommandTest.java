package com.dk.catalogclient;


import com.dk.catalogclient.config.CatalogConfiguration;
import com.dk.catalogclient.response.FetchWorksResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import java.net.URI;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class FetchWorksCommandTest {


    @Mock
    WebResource webResource;
    @Mock
    WebResource.Builder builder;
    private FetchWorksCommand fetchWorksCommand;
    private ObjectMapper mapper;
    @Mock
    private ClientResponse clientResponse;
    @Mock
    private Client client;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CatalogConfiguration catalogConfiguration = new CatalogConfiguration();
        catalogConfiguration.setUrl("randomUrl");
        fetchWorksCommand = new FetchWorksCommand(client, catalogConfiguration);
        mapper = new XmlMapper();
    }

    @Test
    public void shouldReturnWorksResponse() throws Exception {
        FetchWorksResponse fetchWorksResponse = fetchWorksResponse();
        when(client.resource(any(URI.class))).thenReturn(webResource);
        when(webResource.accept(any(MediaType.class))).thenReturn(builder);
        when(webResource.type(any(MediaType.class))).thenReturn(builder);
        when(builder.get(ClientResponse.class)).thenReturn(clientResponse);
        when(clientResponse.getEntity(FetchWorksResponse.class)).thenReturn(fetchWorksResponse);
        FetchWorksResponse worksResponse = fetchWorksCommand.run();
        Assert.assertEquals(worksResponse, fetchWorksResponse);
    }

    private FetchWorksResponse fetchWorksResponse() {
        FetchWorksResponse response = new FetchWorksResponse();

        FetchWorksResponse.Work work = new FetchWorksResponse.Work();
        work.setId(1);

        FetchWorksResponse.Work.Url url = new FetchWorksResponse.Work.Url();
        url.setType("small");
        url.setValue("Some URL");

        work.setUrl(newArrayList(url));

        FetchWorksResponse.Work.Exif exif = new FetchWorksResponse.Work.Exif();
        exif.setMake("Sample Make");
        exif.setModel("Sample Model");
        work.setExif(exif);

        response.setWork(newArrayList(work));

        return response;
    }

}