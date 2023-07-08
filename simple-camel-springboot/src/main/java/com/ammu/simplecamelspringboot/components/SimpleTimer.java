package com.ammu.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:simple-timer?period=2000")
                .routeId("simpleTimerId")
                .setBody(constant("Hello World"))
                .log(LoggingLevel.INFO, "${body}");

    }
}
