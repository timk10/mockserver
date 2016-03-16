package com.mockserver;

import com.mockserver.health.MockServerHealthCheck;
import com.mockserver.resources.ExchangeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class MockServerApplication extends Application<MockServerConfiguration> {
    public static void main(String[] args) throws Exception {
        new MockServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "mock-server";
    }

    @Override
    public void initialize(Bootstrap<MockServerConfiguration> bootstrap) {
    }

    @Override
    public void run(MockServerConfiguration configuration,
                    Environment environment) {

        final MockServerHealthCheck healthCheck = new MockServerHealthCheck();
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(new ExchangeResource());

    }

}
