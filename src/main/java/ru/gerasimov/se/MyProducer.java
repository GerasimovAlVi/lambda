package ru.gerasimov.se;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.producer.ProducerCreator;

import java.util.Scanner;

@Component
public class MyProducer {

    private static final String TOPIC_NAME = "test";
    @Autowired
    private MyConsumer myConsumer;
    @Autowired
    private ProducerCreator producerCreator;

    public void start(){
        Producer<String, String> producer = producerCreator.createProducer();
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите свое имя: ");
            String nickname = scanner.nextLine();
            System.out.println("Привет, " + nickname);

            myConsumer.setGroupId(nickname);
            myConsumer.start();

            String message = "";
            while (!"exit".equals(message)) {
                message = scanner.nextLine();
                producer.send(new ProducerRecord(TOPIC_NAME, nickname + ": " + message));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
