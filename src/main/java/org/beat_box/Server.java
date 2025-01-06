package org.beat_box;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    ArrayList<ObjectOutputStream> clientOutputStreams;

    public static void main(String[] args) {
        Server server = new Server();
        server.go();
    }

    public void go() {
        clientOutputStreams = new ArrayList<>();
        try{
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(objectOut);

                Thread tread = new Thread(new ClientHandler(clientSocket));
                tread.start();
                System.out.println("Client connected! \nClients connected: " + clientOutputStreams.size());
            }

        } catch (IOException ex){
            System.out.println("Server Error!");
            ex.printStackTrace();
        }
    }

    public void tellEveryone(ChatMessage chatMessage) {

        Iterator<ObjectOutputStream> it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try{
                ObjectOutputStream objectOut = it.next();
                objectOut.writeObject(chatMessage);
                objectOut.flush();

            } catch (Exception ex){
                System.out.println("Error sending message!");
                ex.printStackTrace();
            }
        }
    }

    public class ClientHandler implements Runnable {
        ObjectInputStream objectIn;
        Socket socket;

        public ClientHandler(Socket socket) {
            try{
                this.socket = socket;
                objectIn = new ObjectInputStream(socket.getInputStream());

            } catch (IOException ex) {
                System.out.println("Error opening input stream for thread!");
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            ChatMessage chatMessage;

            try{
                while ((chatMessage = (ChatMessage) objectIn.readObject()) != null){
                    System.out.println();
                    System.out.println("Server reads: ");
                    chatMessage.printCurrentCheckBoxes();
                    tellEveryone(chatMessage);

                }
            } catch (Exception ex){
                System.out.println("Error reading messages!");
                ex.printStackTrace();
            }

        }
    }
}
