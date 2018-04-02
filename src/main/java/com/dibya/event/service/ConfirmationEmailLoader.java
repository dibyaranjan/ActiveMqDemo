package com.dibya.event.service;

import com.dibya.event.jms.factory.MQDestination;
import com.dibya.event.vo.Confirmation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;

@Component
public class ConfirmationEmailLoader {
    @JmsListener(destination = "conf-email", containerFactory = "containerFactory")
    public void receive(Message c) {
        if (c instanceof  ActiveMQTextMessage) {
            ActiveMQTextMessage message = (ActiveMQTextMessage) c;
            try {
                String text = message.getText();
                ObjectMapper o = new ObjectMapper();
                Confirmation confirmation = o.readValue(text, Confirmation.class);
                System.out.println(confirmation);
                System.out.println("Sending an email..");
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
