package com.dibya.event.service;

import com.dibya.event.jms.factory.JmsTemplateFactory;
import com.dibya.event.jms.factory.MQDestination;
import com.dibya.event.vo.Confirmation;
import com.dibya.event.vo.Passenger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.ArrayList;

@Service
public class ConfirmationPageLoader {
    @Autowired
    private JmsTemplateFactory templateFactory;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendConfimationPageLoadNotification () {
        final Confirmation c = new Confirmation();
        c.setPnrLocator(message);

        JmsTemplate jmsTemplate = templateFactory.getJmsTemplate(MQDestination.CONFIMATION_PAGE);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMapper o = new ObjectMapper();

                try {
                    return session.createTextMessage(o.writeValueAsString(c));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        JmsTemplate jmsTemplateEmail = templateFactory.getJmsTemplate(MQDestination.CONFIMATION_EMAIL);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMapper o = new ObjectMapper();

                try {
                    c.setPassengers(new ArrayList<Passenger>());
                    return session.createTextMessage(o.writeValueAsString(c));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
