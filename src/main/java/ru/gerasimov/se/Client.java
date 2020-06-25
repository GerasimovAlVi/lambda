package ru.gerasimov.se;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.api.service.IMessageService;
import ru.gerasimov.se.api.service.IUserService;
import ru.gerasimov.se.entity.User;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

@Component
public class Client implements Runnable {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IMessageService iMessageService;
    @Autowired
    private Listener listener;

    public void run(){
        try(Socket socket = new Socket("127.0.0.1",4888);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

            listener.setSocket(socket);
            new Thread(listener).start();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите свое имя: ");
            String nickname = scanner.nextLine();
            User user = iUserService.saveUser(nickname);
            System.out.println("Привет, " + nickname);
            String message = "";
            while (!"exit".equals(message)) {
                message = scanner.nextLine();
                iMessageService.saveMessage(message, user);
                bufferedWriter.write(nickname + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
