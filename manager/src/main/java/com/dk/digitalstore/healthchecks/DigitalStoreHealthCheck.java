package com.dk.digitalstore.healthchecks;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by dkulkarni on 29/11/15.
 */
public class DigitalStoreHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
