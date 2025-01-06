package org.beat_box;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class InstrumentsBox implements Serializable {

    private transient ArrayList<JCheckBox> checkBoxesList;
    static final String[] INSTRUMENTS_NAME = {"Bass Drum", "Closed Hi-Hat",
            "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap",
            "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
            "Open Hi Conga"};
    static final int[] INSTRUMENTS = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    static final int totBeats = 16;
    int[][] selectedInstruments = new int[INSTRUMENTS.length][totBeats];


    public void initializeCheckBoxes() {
        checkBoxesList = new ArrayList<>();

        for (int i = 0; i < INSTRUMENTS.length; i++) {
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

    public int[][] syncInstrumentsWithGui(){

        for (int i = 0; i < INSTRUMENTS.length; i++) {
            int instrumentKey = INSTRUMENTS[i];

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

    public int[][] getSelectedInstruments() {
        return selectedInstruments;
    }

    public void setSelectedInstruments(int[][] selectedInstruments) {
        this.selectedInstruments = selectedInstruments;

        if (checkBoxesList != null && !checkBoxesList.isEmpty()){
            this.updateCheckBoxes();
        }

    }

    public void updateCheckBoxes() {
        for (int i = 0; i < INSTRUMENTS.length; i++) {
            for (int j = 0; j < totBeats; j++) {

                checkBoxesList.get(i*totBeats+j).setSelected(selectedInstruments[i][j] != 0);
            }
        }
    }

    // for debugging
    public void printCurrentCheckBoxes() {
        for (int[] instrument : this.selectedInstruments) {
            for (int i : instrument) {
                if (i == 0) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
    }
}
