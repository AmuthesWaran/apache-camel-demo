package com.ammu.simplecamelspringboot.components.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "LegacyCsvFileRoute", havingValue = "enabled")
public class LegacyCsvFileRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {
//        https://camel.apache.org/components/3.21.x/eips/split-eip.html
        from("file:src/data/input?fileName=sampledata.csv")
                .routeId("legacyCsvFileMoveRouteId")
                .split(body().tokenize("\n",1,true))
                .process(exchange -> {
                        String filedate = exchange.getIn().getBody(String.class);
                        logger.info("This is read file data: "+ filedate);
                })
                .to("file:src/data/output?fileName=outputsampledata.csv&fileExist=append&appendChars=\\n");

    }
}
