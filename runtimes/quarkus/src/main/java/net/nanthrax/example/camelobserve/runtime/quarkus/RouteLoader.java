package net.nanthrax.example.camelobserve.runtime.quarkus;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

import jakarta.inject.Inject;
import net.nanthrax.examples.camelobserve.routes.triggerlog.TriggerLogRoute;

public class RouteLoader extends RouteBuilder {

    @Inject
    private CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        new TriggerLogRoute().configure();
    }
    
}
