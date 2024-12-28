package org.example;

import javax.swing.*;
import java.awt.*;

public class MyDrawingPanel extends JPanel {

    int xAnimation = 0;
    int yAnimation = 0;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Circle dimensions
        int diameter = 100;
        int xCircle = (this.getWidth() - diameter) / 2 + xAnimation;
        int yCircle = (this.getHeight() - diameter) / 2 + yAnimation;

        GradientPaint gradientPaint = new GradientPaint(xCircle, yCircle, this.getRandomColor(), xCircle+diameter, yCircle+diameter, this.getRandomColor());
        g2d.setPaint(gradientPaint);

        g2d.fillOval(xCircle, yCircle, diameter, diameter);
    }

    public Color getRandomColor(){
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);

        return new Color(r,g,b);
    }

    public void incrementAnimation(){
        xAnimation++;
        yAnimation++;
    }

}
