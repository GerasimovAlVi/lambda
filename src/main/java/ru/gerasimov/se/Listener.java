package ru.gerasimov.se;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@Component
public class Listener implements Runnable {

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while(true){
                System.out.println(bufferedReader.readLine());
            }
        }catch(Exception e){
            System.out.println("Вы вышли из чата!");
        }
    }
}
