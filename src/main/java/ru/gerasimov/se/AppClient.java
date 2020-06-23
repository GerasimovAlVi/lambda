package ru.gerasimov.se;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.gerasimov.se");
        final Client client = context.getBean(Client.class);
        AppServerClient.getExecutorService().execute(client);
        AppServerClient.getExecutorService().shutdown();
    }
}
