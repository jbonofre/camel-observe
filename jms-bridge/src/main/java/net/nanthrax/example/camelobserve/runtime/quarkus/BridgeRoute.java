package net.nanthrax.example.camelobserve.runtime.quarkus;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BridgeRoute extends RouteBuilder {

    @Inject
    @Identifier("ibmMQConnectionFactory")
    private MQConnectionFactory ibmMQConnectionFactory;

    @Inject
    @Identifier("activeMQConnectionFactory")
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    @Override
    public void configure() throws Exception {
        from("jms:queue:input?connectionFactory=#ibmMQConnectionFactory&concurrentConsumers=10&acknowledgementModeName=CLIENT_ACKNOWLEDGE")
            .to("jms:queue:output?connectionFactory=#activeMQConnectionFactory");
    }
    
}
