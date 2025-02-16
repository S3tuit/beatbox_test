package org.beat_box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BeatBoxApp{

    private BeatBoxGui beatBoxGui;
    private MidiBeatBox midiBeatBox;
    private Chat chat;

    public static void main(String[] args) {
        BeatBoxApp bba = new BeatBoxApp();
    }

    // creates the entities and start the app
    public BeatBoxApp() {
        beatBoxGui = new BeatBoxGui();
        midiBeatBox = new MidiBeatBox();

        ActionListener startAC = new StartButtonListener();
        ActionListener stopAC = new StopButtonListener();
        ActionListener upTempoAC = new UpTempoButtonListener();
        ActionListener downTempoAC = new DownTempoButtonListener();
        ActionListener saveAC = new SaveButtonListener();
        ActionListener loadAC = new LoadButtonListener();
        ActionListener sendItAC = new SendItButtonListener();
        beatBoxGui.buildGui(startAC, stopAC, upTempoAC, downTempoAC, saveAC, loadAC, sendItAC);
        chat = new Chat(beatBoxGui.getOutgoingMsg(), beatBoxGui.getIncomingMsgPanel(), beatBoxGui);
        chat.start();
    }

    public void buildTrackAndStart(){
        midiBeatBox.setNewTrack();

        // for each instrument, add the beats to the track
        for (int[] instrumentSelection: beatBoxGui.getSelectedInstruments()){
            midiBeatBox.makeTracks(instrumentSelection);
        }

        // Add a dummy event so the track will play until beat 16 (tick: 15)
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

    public class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // gets the current instrument selected based on the GUI's checkboxes
            int[][] selectedInstrument = beatBoxGui.getSelectedInstruments();

            try{
                FileOutputStream fileStream = new FileOutputStream(new File("song.ser"));
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                objectStream.writeObject(selectedInstrument);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[][] selectedInstrument = null;
            try{
                FileInputStream fileStream = new FileInputStream(new File("song.ser"));
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                selectedInstrument = (int[][]) objectStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // update the GUI
            beatBoxGui.setSelectedInstruments(selectedInstrument);
            midiBeatBox.stop();
        }
    }

    public class SendItButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[][] selectedInstrument = beatBoxGui.getSelectedInstruments();
            chat.sendMsg(selectedInstrument);
        }
    }
}