package org.example;

import javax.sound.midi.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatBoxApp{

    BeatBoxGui beatBoxGui;
    MidiBeatBox midiBeatBox;

    public static void main(String[] args) {
        BeatBoxApp bba = new BeatBoxApp();
    }

    public BeatBoxApp() {
        beatBoxGui = new BeatBoxGui();
        midiBeatBox = new MidiBeatBox();

        ActionListener startAC = new StartButtonListener();
        ActionListener stopAC = new StopButtonListener();
        ActionListener upTempoAC = new UpTempoButtonListener();
        ActionListener downTempoAC = new DownTempoButtonListener();
        beatBoxGui.builGui(startAC, stopAC, upTempoAC, downTempoAC);
    }

    public void buildTrackAndStart(){
        int[] trackList = new int[16];
        Track track = midiBeatBox.getNewTrack();

        for (int i = 0; i < trackList.length; i++){
            int key = beatBoxGui.getInstrument(i);

            for (int j = 0; j < beatBoxGui.instruments.length; j++){
                if (beatBoxGui.isCheckBoxSelected(j + 16*i)){
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }

            midiBeatBox.makeTracks(trackList);
        }

        midiBeatBox.addEvent(192, 9, 1, 0, 15);
        midiBeatBox.play();
    }

    public class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    public class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            midiBeatBox.stop();
        }
    }

    public class UpTempoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            midiBeatBox.upTempo();
        }
    }

    public class DownTempoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            midiBeatBox.downTempo();
        }
    }
}