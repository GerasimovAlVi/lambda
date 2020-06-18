package ru.gerasimov.se;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        final EntityManager entityManager = Persistence.createEntityManagerFactory("TEST").createEntityManager();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gerasimov.se");
        final MyProducer myProducer = context.getBean(MyProducer.class);
        myProducer.start(entityManager);
    }
}
