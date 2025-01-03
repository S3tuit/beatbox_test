package org.example;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class InstrumentsBox implements Serializable {

    private transient ArrayList<JCheckBox> checkBoxesList;
    private transient final String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat",
            "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};
    private transient final int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    private transient final int totBeats = 16;
    int[][] selectedInstruments = new int[instruments.length][totBeats];

    public InstrumentsBox() {
        checkBoxesList = new ArrayList<>();
        this.initializeCheckBoxes();
    }

    private void initializeCheckBoxes() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = 0; j < totBeats; j++) {

                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(false);
                checkBoxesList.add(checkBox);
            }
        }
    }

    public ArrayList<JCheckBox> getCheckBoxesList() {
        return checkBoxesList;
    }

    public String[] getInstrumentNames() {
        return instrumentNames;
    }

    public int[][] getSelectedInstruments(){

        for (int i = 0; i < instruments.length; i++) {
            int instrumentKey = instruments[i];

            for (int j = 0; j < totBeats; j++) {
                if (checkBoxesList.get(i*totBeats+j).isSelected()) {
                    selectedInstruments[i][j] = instrumentKey;
                } else {
                    selectedInstruments[i][j] = 0;
                }
            }
        }

        return selectedInstruments;
    }

    public void setSelectedInstruments(int[][] selectedInstruments) {
        this.selectedInstruments = selectedInstruments;
        this.updateCheckBoxes();
    }

    public void updateCheckBoxes() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = 0; j < totBeats; j++) {

                checkBoxesList.get(i*totBeats+j).setSelected(selectedInstruments[i][j] != 0);
            }
        }
    }
}
