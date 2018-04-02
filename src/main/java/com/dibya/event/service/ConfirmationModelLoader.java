package com.dibya.event.service;

import com.dibya.event.vo.Confirmation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.IOException;

@Component
public class ConfirmationModelLoader {
    @JmsListener(destination = "conf-page", containerFactory = "containerFactory")
    public void receive(Object c) {
        if (c instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage message = (ActiveMQTextMessage) c;
            try {
                String text = message.getText();
                ObjectMapper o = new ObjectMapper();
                Confirmation confirmation = o.readValue(text, Confirmation.class);
                System.out.println(confirmation);
                System.out.println("Loading the page");
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
