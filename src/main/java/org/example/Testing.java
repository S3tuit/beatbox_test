package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Testing {

    JButton button;
    JRadioButton radioButton;
    MyDrawingPanel myDrawingPanel;

    public static void main(String[] args) {
        Testing testing = new Testing();
        testing.go();

    }

    public void go(){
        JFrame frame = new JFrame("Testing");
        button = new JButton("Click Me");
        radioButton = new JRadioButton("Radio Button");
        myDrawingPanel = new MyDrawingPanel();

        button.addActionListener(new ButtonListener());
        button.addActionListener(new RadioButtonListener());

        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, myDrawingPanel);
        frame.getContentPane().add(BorderLayout.WEST, radioButton);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    class RadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButton.setText("Giuseppe Sandokan");
            myDrawingPanel.repaint();
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            button.setText("I've been Clicked");

            for (int i=0; i<100; i++){
                myDrawingPanel.incrementAnimation();
                myDrawingPanel.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {}
            }
        }
    }

}
