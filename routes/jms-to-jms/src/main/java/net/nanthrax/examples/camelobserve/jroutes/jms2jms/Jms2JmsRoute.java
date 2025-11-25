package net.nanthrax.examples.camelobserve.jroutes.jms2jms;

import org.apache.camel.builder.RouteBuilder;

public class Jms2JmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // ConnectionFactory is injected at runtime, this route expects one and exactly ONE ConnectionFactory
        from("jms:queue:input")
            .to("jms:queue:output");
    }
    
}
