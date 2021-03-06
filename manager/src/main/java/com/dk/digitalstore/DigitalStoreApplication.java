package com.dk.digitalstore;

import com.dk.catalogclient.config.CatalogModule;
import com.dk.digitalstore.bundle.UiBundle;
import com.dk.digitalstore.config.DigitalStoreModule;
import com.dk.digitalstore.healthchecks.DigitalStoreHealthCheck;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DigitalStoreApplication extends Application<DigitalStoreConfiguration> {

    public static void main(String[] args) throws Exception {
        new DigitalStoreApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DigitalStoreConfiguration> bootstrap) {
        GuiceBundle.Builder<DigitalStoreConfiguration> guiceBundleBuilder = GuiceBundle.newBuilder();
        GuiceBundle<DigitalStoreConfiguration> guiceBundle = guiceBundleBuilder
                .setConfigClass(DigitalStoreConfiguration.class)
                .addModule(new DigitalStoreModule())
                .addModule(new CatalogModule())
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new UiBundle<>());
    }

    @Override
    public void run(DigitalStoreConfiguration digitalStoreConfiguration, Environment environment) throws Exception {
        environment.healthChecks().register("digital-store", new DigitalStoreHealthCheck());
        environment.jersey().setUrlPattern("/api/*");
    }
}
