package com.ammu.simplecamelspringboot.components.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "LegacyFileRoute", havingValue = "enabled")
public class LegacyFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

//        https://camel.apache.org/components/3.21.x/file-component.html
        from("file:src/data/input?fileName=inputFile.txt")
                .routeId("legacyFileMoveRouteId")
                .to("file:src/data/output?fileName=outputFile.txt");

    }
}
