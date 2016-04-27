package com.dk.digitalstore.config;

import com.dk.catalogclient.config.CatalogConfiguration;
import com.dk.digitalstore.DigitalStoreConfiguration;
import com.dk.digitalstore.resources.WorksResource;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DigitalStoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(WorksResource.class).in(Singleton.class);
        bind(Mapper.class).to(DozerBeanMapper.class).in(Singleton.class);
    }

    @Provides
    public CatalogConfiguration providesCatalogConfiguration(Provider<DigitalStoreConfiguration> digitalStoreConfigurationProvider) {
        return digitalStoreConfigurationProvider
                .get()
                .getCatalogConfiguration();
    }
}
