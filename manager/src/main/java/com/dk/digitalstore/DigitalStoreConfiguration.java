package com.dk.digitalstore;

import com.dk.catalogclient.config.CatalogConfiguration;
import io.dropwizard.Configuration;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by dkulkarni on 29/11/15.
 */

@Data
public class DigitalStoreConfiguration extends Configuration {

    @Valid
    @NotNull
    private CatalogConfiguration catalogConfiguration;

}
