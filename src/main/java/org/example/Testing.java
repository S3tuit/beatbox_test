package org.example;

import javax.swing.*;

public class Testing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        JButton button = new JButton("Click Me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(button);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
