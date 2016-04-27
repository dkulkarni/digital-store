package com.dk.catalogclient;


import com.dk.catalogclient.response.FetchWorksResponse;

/**
 * Created by dkulkarni on 25/04/16.
 */
public interface CatalogClient {

    FetchWorksResponse fetchWorks();
}
