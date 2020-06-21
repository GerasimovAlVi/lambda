package ru.gerasimov.se;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.api.service.IMessageService;
import ru.gerasimov.se.api.service.ISettingsService;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.User;
import ru.gerasimov.se.entity.settings.ApplicationSettings;
import ru.gerasimov.se.entity.settings.DeveloperSettings;
import ru.gerasimov.se.entity.settings.Settings;
import ru.gerasimov.se.producer.ProducerCreator;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private ISettingsService iSettingsService;

    public void start(EntityManager em){
        //kafka:
//        Producer<String, String> producer = producerCreator.createProducer();
        try{
            //start cache
//            final EntityManager em1 = Persistence.createEntityManagerFactory("TEST").createEntityManager();
//            em1.getTransaction().begin();
//            System.out.println("----------------------------------------------------------------------------------------------------");
//            User user1 = em1.find(User.class, 1L);
//            System.out.println("----------------------------------------------------------------------------------------------------");
//            em1.getTransaction().commit();
//
//            final EntityManager em2 = Persistence.createEntityManagerFactory("TEST").createEntityManager();
//            em2.getTransaction().begin();
//            System.out.println("----------------------------------------------------------------------------------------------------");
//            User user2 = em2.find(User.class, 1L);
//            System.out.println("----------------------------------------------------------------------------------------------------");
//            em2.getTransaction().commit();
            //end cache

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите свое имя: ");
            String nickname = scanner.nextLine();
            User user = iUserService.saveUser(em, nickname);
            System.out.println("Привет, " + nickname);
            //kafka:
//            myConsumer.setGroupId(nickname);
//            myConsumer.start();

            //start settings:
            System.out.print("Введите свои ФИО: ");
            String fio = scanner.nextLine();
            System.out.print("Введите свой email: ");
            String email = scanner.nextLine();
            System.out.print("Введите ОС Вашего ПК: ");
            String system = scanner.nextLine();
            System.out.print("Введите объем оперативной памяти Вашего ПК: ");
            String memory = scanner.nextLine();
            System.out.println();
            List<Settings> settingsList = new ArrayList<>();
            settingsList.add(new DeveloperSettings(fio, email));
            settingsList.add(new ApplicationSettings(system, memory));
            iSettingsService.saveMessage(em, settingsList);
            //end settings

            System.out.println("Введите сообщение: ");
            String message = "";
            while (!"exit".equals(message)) {
                message = scanner.nextLine();
                iMessageService.saveMessage(em, message, user);
                //kafka:
//                producer.send(new ProducerRecord(TOPIC_NAME, nickname + ": " + message));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
