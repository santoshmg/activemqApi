package com.sample.sqs.activemqApi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqApiApplicationTests {

	@Autowired
	private Producer producer;

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void contextLoads() {
	}

	@Test
	public void sendMessage() throws Exception {
		String message = "Hello World";
		producer.sendMessage(message);
		Thread.sleep(1000);
		assertThat(this.outputCapture.toString().contains(message));
	}

	@Test
	public void sendEmailMessage() throws Exception {
		producer.send(email());
		Thread.sleep(1000);
		assertThat(this.outputCapture.toString().contains(email().toString()));
	}

	private Email email() {
		Email email = new Email();
		email.setTo("john.deo@gmail.com");
		email.setBody("Hello John! Happy Birthday.");
		return email;
	}
}
