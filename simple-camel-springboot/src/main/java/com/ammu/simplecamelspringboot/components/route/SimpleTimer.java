package com.ammu.simplecamelspringboot.components.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "SimpleTimer", havingValue = "enabled")
public class SimpleTimer extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:simple-timer?period=2000")
                .routeId("simpleTimerId")
                .setBody(constant("Hello World"))
                .log(LoggingLevel.INFO, "${body}");

    }
}
