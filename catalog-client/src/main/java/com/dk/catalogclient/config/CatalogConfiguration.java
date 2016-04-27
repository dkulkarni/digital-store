package com.dk.catalogclient.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class CatalogConfiguration {

    @Valid
    @NotNull
    @URL
    private String url;
}
