package com.dk.catalogclient.config;

import com.dk.catalogclient.CatalogClient;
import com.dk.catalogclient.HystrixCatalogClient;
import com.google.inject.AbstractModule;


public class CatalogModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CatalogClient.class).to(HystrixCatalogClient.class);
    }
}
