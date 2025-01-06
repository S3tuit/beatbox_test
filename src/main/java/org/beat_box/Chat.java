package org.beat_box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Chat {

    // references to the GUI
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

    // connects to the server and starts a new thread to read incoming messages
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

            // deep copy so the obj will be correctly send
            int[][] copiedInstrument = new int[selectedInstrument.length][];
            for (int i = 0; i < selectedInstrument.length; i++) {
                copiedInstrument[i] = selectedInstrument[i].clone();
            }

            ChatMessage chatMessage = new ChatMessage(outgoingMsg.getText(), copiedInstrument);

            objOutStream.writeObject(chatMessage);

            // debug
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

                    // creates a new panel to hold the message
                    JPanel messagePanel = new JPanel(new BorderLayout(5,5));
                    messagePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

                    // add the message to the panel
                    JLabel messageLabel = new JLabel(chatMessage.getMessage());
                    messagePanel.add(messageLabel, BorderLayout.CENTER);

                    // add the "Load Pattern" button to the panel
                    JButton loadPatternButton = new JButton("Load Pattern");
                    loadPatternButton.addActionListener(new LoadPatternButtonListener(chatMessage));
                    messagePanel.add(loadPatternButton, BorderLayout.EAST);

                    incomingMsgPanel.add(messagePanel, BorderLayout.CENTER);
                    incomingMsgPanel.revalidate();
                    incomingMsgPanel.repaint();

                    // debug
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

        // reference to the chatMessage that holds the beat pattern the button will load
        ChatMessage chatMessage;

        public LoadPatternButtonListener(ChatMessage chatMessage) {
            this.chatMessage = chatMessage;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // load the beat pattern into the GUI
            beatBoxGui.setSelectedInstruments(chatMessage.getSelectedInstruments());
        }
    }
}
