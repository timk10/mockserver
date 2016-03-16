package com.mockserver.health;

/**
 * Created by tim on 3/15/16.
 */

import com.codahale.metrics.health.HealthCheck;

public class MockServerHealthCheck extends HealthCheck {

    public MockServerHealthCheck() {

    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
