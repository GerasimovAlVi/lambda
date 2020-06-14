package ru.gerasimov.se;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gerasimov.se");
        final MyProducer myProducer = context.getBean(MyProducer.class);
        myProducer.start();
    }
}
