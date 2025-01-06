package org.beat_box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BeatBoxGui {

    private JFrame frame;
    private JPanel mainPanel;
    private InstrumentsBox instrumentsBox;
    private JTextField outgoingMsg;
    private JPanel incomingMsgPanel;


    public void buildGui(ActionListener startAC, ActionListener stopAC, ActionListener upTempoAC,
                        ActionListener downTempoAC, ActionListener saveAC, ActionListener loadAC,
                         ActionListener sendItAC) {

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        frame = new JFrame("BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        instrumentsBox = new InstrumentsBox();
        instrumentsBox.initializeCheckBoxes();

        // Instrument Panel - WEST
        JPanel instrumentsPanel = createInstrumentPanel();
        frame.add(instrumentsPanel, BorderLayout.WEST);

        // Main grid panel for checkboxes - CENTER
        mainPanel = new JPanel(new GridLayout(16, 16, 2, 2));
        for (JCheckBox checkBox : instrumentsBox.getCheckBoxesList()) {
            mainPanel.add(checkBox);
        }
        frame.add(mainPanel, BorderLayout.CENTER);

        // Button and Chat Panel - EAST
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.add(createButtonPanel(startAC, stopAC, upTempoAC, downTempoAC, saveAC, loadAC, sendItAC), BorderLayout.NORTH);
        rightPanel.add(createChatPanel(), BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        // Frame settings
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createInstrumentPanel() {
        JPanel instrumentsPanel = new JPanel(new GridLayout(16, 2, 5, 5));
        instrumentsPanel.setBorder(BorderFactory.createTitledBorder("Instruments"));

        String[] instrumentNames = InstrumentsBox.INSTRUMENTS_NAME;

        for (String instrumentName : instrumentNames) {
            JLabel instrumentLabel = new JLabel(instrumentName);
            instrumentLabel.setHorizontalAlignment(SwingConstants.LEFT);
            instrumentsPanel.add(instrumentLabel);
        }

        return instrumentsPanel;
    }

    private JPanel createButtonPanel(ActionListener startAC, ActionListener stopAC, ActionListener upTempoAC,
                                     ActionListener downTempoAC, ActionListener saveAC, ActionListener loadAC,
                                     ActionListener sendItAC){
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        JButton startButton = createButton("Start", startAC);
        JButton stopButton = createButton("Stop", stopAC);
        JButton upTempoButton = createButton("Tempo Up", upTempoAC);
        JButton downTempoButton = createButton("Tempo Down", downTempoAC);
        JButton saveButton = createButton("Save", saveAC);
        JButton loadButton = createButton("Load", loadAC);
        JButton sendItButton = createButton("Send It", sendItAC);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(upTempoButton);
        buttonPanel.add(downTempoButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(sendItButton);

        return buttonPanel;
    }

    private JPanel createChatPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout(5, 5));
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat"));

        incomingMsgPanel = new JPanel();
        incomingMsgPanel.setLayout(new BoxLayout(incomingMsgPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(incomingMsgPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        outgoingMsg = new JTextField();
        outgoingMsg.setBorder(BorderFactory.createTitledBorder("Message"));

        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(outgoingMsg, BorderLayout.SOUTH);

        return chatPanel;
    }

    private JButton createButton(String text, ActionListener actionListener){
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    public int[][] getSelectedInstruments(){
        return instrumentsBox.syncInstrumentsWithGui();
    }

    public void setSelectedInstruments(int[][] selectedInstruments){
        instrumentsBox.setSelectedInstruments(selectedInstruments);
    }

    public void printCurrentCheckBoxes(){
        instrumentsBox.printCurrentCheckBoxes();
    }

    public JPanel getIncomingMsgPanel(){
        return incomingMsgPanel;
    }

    public JTextField getOutgoingMsg() {
        return outgoingMsg;
    }
}
