package com.dk.digitalstore;

import com.dk.catalogclient.config.CatalogConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by dkulkarni on 29/11/15.
 */

@Data
public class DigitalStoreConfiguration extends Configuration {

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration = new SwaggerBundleConfiguration();
    @Valid
    @NotNull
    private CatalogConfiguration catalogConfiguration;


}
