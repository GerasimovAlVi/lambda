package ru.gerasimov.se;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.function.Consumer;

@Component
public class Listener implements Runnable {

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Consumer<String> soutConsumer = System.out::println;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while(true){
                soutConsumer.accept(bufferedReader.readLine());
            }
        }catch(Exception e){
            soutConsumer.accept("Вы вышли из чата!");
        }
    }
}
