package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {

    JTextField outgoingMsg;
    JTextArea incomingMsg;
    PrintWriter writer;
    BufferedReader reader;
    Socket socket;

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.go();
    }

    public void go(){
        JFrame frame = new JFrame("Chat");
        JPanel mainPanel = new JPanel();
        incomingMsg = new JTextArea(15, 50);
        incomingMsg.setLineWrap(true);
        incomingMsg.setWrapStyleWord(true);
        incomingMsg.setEditable(false);
        JScrollPane incomingScroller = new JScrollPane(incomingMsg);
        incomingScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        incomingScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        outgoingMsg = new JTextField(20);

        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(new SendButtonListener());

        mainPanel.add(incomingScroller);
        mainPanel.add(outgoingMsg);
        mainPanel.add(sendBtn);

        this.setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);

    }

    private void setUpNetworking(){
        try{
            socket = new Socket("127.0.0.1", 4242);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isr);

            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to server");

        } catch (IOException e) {
            System.out.println("Failed to connect to server");
            e.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoingMsg.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoingMsg.setText("");
            outgoingMsg.requestFocus();
        }
    }

    public class IncomingReader implements Runnable {
        @Override
        public void run() {
            String message = null;
            try{
                while ((message = reader.readLine()) != null){
                    System.out.println("Read: " + message);
                    incomingMsg.append(message + "\n");
                }

            } catch(IOException ex) {ex.printStackTrace();}
        }
    }
}
