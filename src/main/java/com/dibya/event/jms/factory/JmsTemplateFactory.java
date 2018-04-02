package com.dibya.event.jms.factory;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class JmsTemplateFactory {
    @Autowired
    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate(MQDestination destination) {
        Destination d = null;
        if ("topic".equals(destination.getType())) {
            d = new ActiveMQTopic(destination.getName());
        } else {
            d = new ActiveMQQueue(destination.getName());
        }

        jmsTemplate.setDefaultDestination(d);

        return jmsTemplate;
    }
}
