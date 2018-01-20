package com.sample.sqs.activemqApi;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "sample.message.queue", containerFactory = "jmsContainerFactory")
    public void receiveMessage(String message) {

        System.out.println("Received <" + message + ">");
    }

    @JmsListener(destination = "sample.email.queue", containerFactory = "jmsContainerFactory")
    public void receiveEmail(Email email) {

        System.out.println("Received <" + email.toString() + ">");
    }
}
