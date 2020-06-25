package ru.gerasimov.se;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gerasimov.se.config.ApplicationConfig;

public class AppClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        final Client client = context.getBean(Client.class);
        AppServerClient.getExecutorService().execute(client);
        AppServerClient.getExecutorService().shutdown();
    }
}
