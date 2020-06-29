package ru.gerasimov.se;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class AppServerClient {

    private static final List<ServerClient> SERVER_CLIENT_LIST = new ArrayList<>();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

    public static void main(String[] args) {
        Consumer<String> soutConsumer = System.out::println;
        try(ServerSocket serverSocket = new ServerSocket(4888)){
            soutConsumer.accept("Сервер стартовал!");

            while(true){
                Socket socket = serverSocket.accept();
                soutConsumer.accept("Новый участник присоединился к чату!");
                ServerClient serverClient = new ServerClient(socket);
                serverClientList().add(serverClient);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ExecutorService getExecutorService(){
        return EXECUTOR_SERVICE;
    }

    public static List<ServerClient> serverClientList(){
        return SERVER_CLIENT_LIST;
    }
}
