package org.beat_box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Chat {

    private JTextField outgoingMsg;
    private JPanel incomingMsgPanel;
    private ObjectOutputStream objOutStream;
    private ObjectInputStream objInStream;
    private Socket socket;
    private BeatBoxGui beatBoxGui;

    public Chat(JTextField outgoingMsg, JPanel incomingMsgPanel, BeatBoxGui beatBoxGui) {
        this.outgoingMsg = outgoingMsg;
        this.incomingMsgPanel = incomingMsgPanel;
        this.beatBoxGui = beatBoxGui;
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
            System.out.println("Chat sends: ");
            chatMessage.printCurrentCheckBoxes();

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
            ChatMessage chatMessage;
            try{
                while ((chatMessage = (ChatMessage) objInStream.readObject()) != null){

                    JPanel messagePanel = new JPanel(new BorderLayout(5,5));
                    messagePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

                    JLabel messageLabel = new JLabel(chatMessage.getMessage());
                    messagePanel.add(messageLabel, BorderLayout.CENTER);

                    JButton loadPatternButton = new JButton("Load Pattern");
                    loadPatternButton.addActionListener(new LoadPatternButtonListener(chatMessage));
                    messagePanel.add(loadPatternButton, BorderLayout.EAST);

                    incomingMsgPanel.add(messagePanel, BorderLayout.CENTER);
                    incomingMsgPanel.revalidate();
                    incomingMsgPanel.repaint();

                    System.out.println("Chat reads: ");
                    chatMessage.printCurrentCheckBoxes();
                }
            } catch (Exception e) {
                System.out.println("Error reading the message in the chat");
                e.printStackTrace();
            }
        }
    }

    public class LoadPatternButtonListener implements ActionListener {

        ChatMessage chatMessage;

        public LoadPatternButtonListener(ChatMessage chatMessage) {
            this.chatMessage = chatMessage;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            beatBoxGui.setSelectedInstruments(chatMessage.getSelectedInstruments());
            System.out.println(chatMessage.getMessage());
            chatMessage.printCurrentCheckBoxes();
            System.out.println();
        }
    }
}
