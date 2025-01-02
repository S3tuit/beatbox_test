package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeatBoxGui {

    JFrame frame;
    ArrayList<JCheckBox> checkBoxesList;
    JPanel mainPanel;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
            "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};


    public void builGui(ActionListener startAC, ActionListener stopAC, ActionListener upTempoAC, ActionListener downTempoAC) {
        frame = new JFrame("BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkBoxesList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(startAC);
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(stopAC);
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(upTempoAC);
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(downTempoAC);
        buttonBox.add(downTempo);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(int i = 0; i < instruments.length; i++){
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(buttonBox, BorderLayout.EAST);
        background.add(nameBox, BorderLayout.WEST);

        frame.getContentPane().add(background, BorderLayout.CENTER);

        GridLayout grid  = new GridLayout(16, 16);
        grid.setHgap(2);
        grid.setVgap(1);
        mainPanel = new JPanel(grid);
        background.add(mainPanel, BorderLayout.CENTER);

        for (int i=0; i<256; i++){
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
            checkBoxesList.add(checkBox);
            mainPanel.add(checkBox);
        }

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public int getInstrument(int idx){
        return instruments[idx];
    }

    public boolean isCheckBoxSelected(int idx){
        return checkBoxesList.get(idx).isSelected();
    }

}
