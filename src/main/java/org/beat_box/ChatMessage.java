package org.beat_box;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ChatMessage implements Serializable {

    private String message;
    private int[][] selectedInstrument;
    JButton loadPatternButton;

    public ChatMessage(String message, int[][] selectedInstrument) {
        this.message = message;
        this.selectedInstrument = selectedInstrument;

        loadPatternButton = new JButton("Load Pattern");
        loadPatternButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadPatternButton.setPreferredSize(new Dimension(100, 30));
        loadPatternButton.setBackground(Color.GRAY);
        loadPatternButton.setOpaque(true);
    }

    public String getMessage() {
        return message;
    }

    public int[][] getSelectedInstrument() {
        return selectedInstrument;
    }

    // for debugging
    public void printCurrentCheckBoxes() {
        for (int[] instrument : this.getSelectedInstrument()) {
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
