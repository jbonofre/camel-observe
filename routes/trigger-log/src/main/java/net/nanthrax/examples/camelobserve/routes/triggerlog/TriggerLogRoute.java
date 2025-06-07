package net.nanthrax.examples.camelobserve.routes.triggerlog;

import org.apache.camel.builder.RouteBuilder;

public class TriggerLogRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:fire?period=5000")
                .setBody(constant("Hello World"))
                .to("log:triggerlog");
    }

}
