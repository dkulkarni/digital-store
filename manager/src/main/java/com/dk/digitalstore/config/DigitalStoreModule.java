package com.dk.digitalstore.config;

import com.dk.digitalstore.resources.WorksResource;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by dkulkarni on 26/04/16.
 */
public class DigitalStoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(WorksResource.class).in(Singleton.class);
    }
}
