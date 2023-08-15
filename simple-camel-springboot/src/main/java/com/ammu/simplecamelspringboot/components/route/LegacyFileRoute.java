package com.ammu.simplecamelspringboot.components.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;



@Component
@ConditionalOnProperty(name = "LegacyFileRoute", havingValue = "enabled")
public class LegacyFileRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {

//        https://camel.apache.org/components/3.21.x/file-component.html
        from("file:src/data/input?fileName=inputFile.txt")
                .routeId("legacyFileMoveRouteId")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                    }
//                })
                .process(exchange -> {
                        String filedate = exchange.getIn().getBody(String.class);
                        logger.info("This is read file data: "+ filedate);
                })
                .to("file:src/data/output?fileName=outputFile.txt");

    }
}
