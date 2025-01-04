package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    ArrayList<PrintWriter> clientOutputStreams;

    public static void main (String[] args) {
        Server server = new Server();
        server.go();
    }

    public void go() {
        clientOutputStreams = new ArrayList<>();
        try{
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread thread = new Thread(new ClientHandeler(clientSocket));
                thread.start();
                System.out.println("Client connected!");
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    public void tellEveryone(String message) {

        Iterator<PrintWriter> it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();

            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public class ClientHandeler implements Runnable {
        BufferedReader reader;
        Socket socket;

        public ClientHandeler(Socket socket) {
            try{
                this.socket = socket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);

            } catch (Exception e) {e.printStackTrace();}
        }

        @Override
        public void run() {
            String message;

            try{
                while ((message = reader.readLine()) != null) {
                    System.out.println("Reader: "+message);
                    tellEveryone(message);

                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
