package ru.gerasimov.se;

import java.io.*;
import java.net.Socket;

public class ServerClient implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    ServerClient(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread(this).start();
    }
    public void run(){
        String message;
        try{
            while (true) {
                message = bufferedReader.readLine();
                if(message.equals("exit")){
                    throw new Exception();
                }
                System.out.println("Сообщение от " + message);
                for (ServerClient i : AppServerClient.serverClientList()) {
                    i.bufferedWriter.write(message + "\n");
                    i.bufferedWriter.flush();
                }
            }
        }catch(Exception e){
            AppServerClient.serverClientList().remove(this);
            System.out.println("Участник покинул чат!");
            try {
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
