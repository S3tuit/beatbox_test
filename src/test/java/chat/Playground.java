package chat;

import javax.swing.*;
import java.awt.*;

public class Playground {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Button Box Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create the main vertical Box
            Box verticalBox = Box.createVerticalBox();

            // Define the uniform button size
            Dimension buttonSize = new Dimension(100, 50);

            // Create the first row (horizontal Box) with the first and second buttons
            Box row1 = Box.createHorizontalBox();
            JButton button1 = new JButton("Button 1");
            JButton button2 = new JButton("Button 2");
            button1.setPreferredSize(buttonSize);
            button2.setPreferredSize(buttonSize);
            row1.add(button1);
            row1.add(Box.createHorizontalStrut(10)); // Add spacing between buttons
            row1.add(button2);

            // Create the second row (horizontal Box) with the third and fourth buttons
            Box row2 = Box.createHorizontalBox();
            JButton button3 = new JButton("Button 3");
            JButton button4 = new JButton("Button 4");
            button3.setPreferredSize(buttonSize);
            button4.setPreferredSize(buttonSize);
            row2.add(button3);
            row2.add(Box.createHorizontalStrut(10)); // Add spacing between buttons
            row2.add(button4);

            // Add rows to the vertical Box
            verticalBox.add(row1);
            verticalBox.add(Box.createVerticalStrut(10)); // Add spacing between rows
            verticalBox.add(row2);

            // Add the vertical Box to the frame
            frame.add(verticalBox, BorderLayout.CENTER);

            // Set up the frame
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
