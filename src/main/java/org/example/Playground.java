package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Playground implements ActionListener, ItemListener {

    JTextArea textArea;
    boolean isCheckBoxChecked = false;
    JCheckBox check;

    public static void main(String[] args) {
        Playground p = new Playground();
        p.go();
    }


    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton play = new JButton("Play");
        check = new JCheckBox("Giuseppe Sandokan");
        play.addActionListener(this);
        check.addItemListener(this);

        textArea = new JTextArea(10,10);
        JScrollPane scroller = new JScrollPane(textArea);
        textArea.setLineWrap(true);

        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroller);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(play, BorderLayout.SOUTH);
        frame.getContentPane().add(check, BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!check.isSelected()){
            textArea.append("Hello World\n");
        } else {
            textArea.append("Giuseppe Sandokan\n");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("ItemStateChanged");
    }
}
