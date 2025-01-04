package org.beat_box;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Chat {

    JTextField outgoingMsg;
    JTextArea incomingMsg;
    ObjectOutputStream objOutStream;
    ObjectInputStream objInStream;
    Socket socket;

    public Chat(JTextField outgoingMsg, JTextArea incomingMsg) {
        this.outgoingMsg = outgoingMsg;
        this.incomingMsg = incomingMsg;

    }

    public void start() {
        this.setUpNetworking();
        Thread thread = new Thread(new IncomingReader());
        thread.start();
    }

    private void setUpNetworking(){
        try{
            socket = new Socket("127.0.0.1", 4242);
            objInStream = new ObjectInputStream(socket.getInputStream());

            objOutStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Connected to server");

        } catch (IOException e) {
            System.out.println("Socket could not be created for the chat");
            e.printStackTrace();
        }
    }

    public void sendMsg(int[][] selectedInstrument) {
        try{
            ChatMessage chatMessage = new ChatMessage(outgoingMsg.getText(), selectedInstrument);
            objOutStream.writeObject(chatMessage);
            objOutStream.flush();

        } catch (Exception ex) {
            System.out.println("Error sending the message from the chat");
            ex.printStackTrace();
        }
        outgoingMsg.setText("");
        outgoingMsg.requestFocus();
    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            ChatMessage chatMessage = null;
            try{
                while ((chatMessage = (ChatMessage) objInStream.readObject()) != null){

                    System.out.println("Read: " + chatMessage.getMessage());
                    incomingMsg.append(chatMessage.getMessage() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Error reading the message in the chat");
                e.printStackTrace();
            }
        }
    }
}
