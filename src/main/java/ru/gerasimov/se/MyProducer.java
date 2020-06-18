package ru.gerasimov.se;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.api.service.IMessageService;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.User;
import ru.gerasimov.se.producer.ProducerCreator;

import javax.persistence.EntityManager;
import java.util.Scanner;

@Component
public class MyProducer {

    private static final String TOPIC_NAME = "test";
    @Autowired
    private MyConsumer myConsumer;
    @Autowired
    private ProducerCreator producerCreator;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IMessageService iMessageService;

    public void start(EntityManager em){
        Producer<String, String> producer = producerCreator.createProducer();
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите свое имя: ");
            String nickname = scanner.nextLine();
            User user = iUserService.saveUser(em, nickname);
            System.out.println("Привет, " + nickname);
            myConsumer.setGroupId(nickname);
            myConsumer.start();

            String message = "";
            while (!"exit".equals(message)) {
                message = scanner.nextLine();
                iMessageService.saveMessage(em, message, user);
                producer.send(new ProducerRecord(TOPIC_NAME, nickname + ": " + message));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
