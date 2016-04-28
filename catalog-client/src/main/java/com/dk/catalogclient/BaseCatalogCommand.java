package com.dk.catalogclient;

import com.google.inject.Inject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class BaseCatalogCommand<T> extends HystrixCommand<T> {
    private static final String CATALOG_GROUP = "catalog";

    protected final Client client;

    @Inject
    public BaseCatalogCommand(Client client) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(CATALOG_GROUP))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.client = client;
    }

    protected void handleErrors(ClientResponse response) {
        if (response.getStatus() >= 400 && response.getStatus() < 500) {
            log.error("Request Failed with {}", response.getStatus());
            throw new HystrixBadRequestException(
                    "Request failed with " + response.getStatus() + response.getEntity(String.class));
        }
        if (response.getStatus() >= 500) {
            log.error("Request Failed with {}", response.getStatus());
            throw new HystrixBadRequestException("Request Failed");
        }
    }
}
