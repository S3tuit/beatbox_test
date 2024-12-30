package org.example;

import javax.sound.midi.*;
import javax.swing.*;

public class BeatBoxApp implements ControllerEventListener{

    //BeatBoxGui beatBoxGui;

    public static void main(String[] args) {
        BeatBoxApp mp = new BeatBoxApp();
        BeatBoxGui beatBoxGui = new BeatBoxGui();
        beatBoxGui.builGui();
    }


    public void go() {

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            // MIDIEvent to listen to
            //sequencer.addControllerEventListener(new int[] {127});

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i = 5; i < 60; i+=4) {

                r = (int) ((Math.random() * 50)+1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i+2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println(event);
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

}