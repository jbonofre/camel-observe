package net.nanthrax.example.camelobserve.runtime.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;

import io.smallrye.common.annotation.Identifier;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.eclipse.microprofile.config.ConfigProvider;

public class Producers {

    @Identifier("ibmMQConnectionFactory")
    public MQConnectionFactory createIBMMQConnectionFactory() {
        MQConnectionFactory factory = new MQConnectionFactory();
        try {
            factory.setHostName(ConfigProvider.getConfig().getValue("ibm.mq.host", String.class));
            factory.setPort(ConfigProvider.getConfig().getValue("ibm.mq.port", Integer.class));
            factory.setChannel(ConfigProvider.getConfig().getValue("ibm.mq.channel", String.class));
            factory.setQueueManager(ConfigProvider.getConfig().getValue("ibm.mq.queueManagerName", String.class));
            factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            factory.setStringProperty(WMQConstants.USERID, ConfigProvider.getConfig().getValue("ibm.mq.user", String.class));
            factory.setStringProperty(WMQConstants.PASSWORD, ConfigProvider.getConfig().getValue("ibm.mq.password", String.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create IBM MQ Connection Factory", e);
        }
        return factory;
    }

    @Identifier("activeMQConnectionFactory")
    public ActiveMQConnectionFactory createActiveMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(ConfigProvider.getConfig().getValue("activemq.brokerURL", String.class));
        factory.setUserName(ConfigProvider.getConfig().getValue("activemq.user", String.class));
        factory.setPassword(ConfigProvider.getConfig().getValue("activemq.password", String.class));
        return factory;
    }
    
}