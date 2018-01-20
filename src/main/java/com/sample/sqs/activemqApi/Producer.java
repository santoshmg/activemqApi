package com.sample.sqs.activemqApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


@Component
public class Producer implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("emailQueue")
    private Queue emailQueue;

    @Autowired
    @Qualifier("messageQueue")
    private Queue messageQueue;

    @Override
    public void run(String... args) throws Exception {
        sendMessage("Hello World");
        System.out.println("Message sent to Queue : " + emailQueue.getQueueName());

        send(email());
        System.out.println("Email details sent to Queue : " + emailQueue.getQueueName());
    }

    public void sendMessage(String message) {
        this.jmsTemplate.convertAndSend(this.messageQueue, message);
    }

    public void send(Object email) {
        this.jmsTemplate.convertAndSend(this.emailQueue, email);
    }

    private Email email() {
        Email email = new Email();
        email.setTo("john.deo@gmail.com");
        email.setBody("Hello John! Happy Birthday.");
        return email;
    }
}
