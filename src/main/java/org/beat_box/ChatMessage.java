package org.beat_box;


public class ChatMessage extends InstrumentsBox {

    private String message;

    public ChatMessage(String message, int[][] selectedInstrument) {
        this.message = message;
        super.setSelectedInstruments(selectedInstrument);
        System.out.println("ChatMessage created");
        printCurrentCheckBoxes();
    }

    public String getMessage() {
        return message;
    }

    // for debugging
    @Override
    public void printCurrentCheckBoxes() {
        System.out.println("Message: \n" + message + "\nPattern: ");
        super.printCurrentCheckBoxes();
    }
}
