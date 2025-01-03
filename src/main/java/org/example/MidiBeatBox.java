package org.example;

import javax.sound.midi.*;

public class MidiBeatBox {

    Sequencer sequencer;
    Sequence sequence;
    Track track;

    public MidiBeatBox() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);

        } catch (Exception e) {e.printStackTrace();}
    }

    public Track setNewTrack() {
        sequence.deleteTrack(track);
        track = sequence.createTrack();
        return track;
    }

    public void addEvent(MidiEvent ev) {
        track.add(ev);
    }

    public void addEvent(int comd, int chan, int one, int two, int tick) {
        track.add(makeEvent(comd, chan, one, two, tick));
    }

    public void makeTracks(int[] list) {

        for (int i = 0; i < list.length; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i+1));
            }
            track.add(makeEvent(176, 1, 127, 0, 16));
        }
    }

    public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(comd, chan, one, two);
            event = new MidiEvent(msg, tick);

        } catch (InvalidMidiDataException e) {}

        return event;
    }

    public void play() {
        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {e.printStackTrace();}
    }

    public void stop(){
        sequencer.stop();
    }

    public void upTempo(){
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float)(tempoFactor*1.03));
    }

    public void downTempo(){
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float)(tempoFactor*0.97));
    }
}
