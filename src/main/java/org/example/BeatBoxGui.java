package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BeatBoxGui {

    JFrame frame;
    JPanel mainPanel;
    InstrumentsBox instrumentsBox;


    public void builGui(ActionListener startAC, ActionListener stopAC, ActionListener upTempoAC, ActionListener downTempoAC, ActionListener saveAC, ActionListener loadAC) {
        frame = new JFrame("BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        instrumentsBox = new InstrumentsBox();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        this.addButtons(buttonBox, startAC, stopAC, upTempoAC, downTempoAC, saveAC, loadAC);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for(String instrumentName : instrumentsBox.getInstrumentNames()) {
            nameBox.add(new Label(instrumentName));
        }

        background.add(buttonBox, BorderLayout.EAST);
        background.add(nameBox, BorderLayout.WEST);

        frame.getContentPane().add(background, BorderLayout.CENTER);

        GridLayout grid  = new GridLayout(16, 16);
        grid.setHgap(2);
        grid.setVgap(1);
        mainPanel = new JPanel(grid);
        background.add(mainPanel, BorderLayout.CENTER);

        for(JCheckBox checkBox: instrumentsBox.getCheckBoxesList()){
            mainPanel.add(checkBox);
        }

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }

    private void addButtons(Box buttonBox, ActionListener startAC, ActionListener stopAC, ActionListener upTempoAC, ActionListener downTempoAC, ActionListener saveAC, ActionListener loadAC){
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

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(saveAC);
        buttonBox.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(loadAC);
        buttonBox.add(loadButton);
    }

    public int[][] getSelectedInstruments(){
        return instrumentsBox.getSelectedInstruments();
    }

    public void setSelectedInstruments(int[][] selectedInstruments){
        instrumentsBox.setSelectedInstruments(selectedInstruments);
    }
}
