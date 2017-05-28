package com.asciipic.journalize.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Statistic extends JPanel {
    private BufferedImage image;
    private Graphics2D graphics;

    public Statistic() {
        image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();

        this.setPreferredSize(new Dimension(1000, 1000));
        this.setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public String drawShape(int totalNumber, int partialNumber) {

        int height = 1000;
        int width = 1000;
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(5));
        //graphics.setBackground(Color.white);

        graphics.drawLine(0, 0, 0, height);
        graphics.drawLine(0, height, width, height);
        graphics.drawLine(0, 0, width, 0);
        graphics.drawLine(width, height, width, 0);

        //lines
        graphics.setColor(Color.RED);
        graphics.drawLine(100, height - 300, width - 200, height - 300);
        graphics.drawLine(100, height - 300, 100, 100);

        graphics.drawLine(100, 100, 80, 120);
        graphics.drawLine(100, 100, 120, 120);
        graphics.drawLine(width - 200, height - 300, width - 220, height - 320);
        graphics.drawLine(width - 200, height - 300, width - 220, height - 280);

        int proportionalHeight = (500 * partialNumber) / totalNumber + 3;
        int totalPlotHeight = height - 800 - 3;
        int partialPlotHeight = height - 300 - proportionalHeight;
        Random rand = new Random();
        Color colorTotalPlot = new Color(rand.nextInt(0xFFFFFF));
        rand = new Random();
        Color colorPartialPlot = new Color(rand.nextInt(0xFFFFFF));
        graphics.setColor(Color.RED);
        graphics.drawLine(90, totalPlotHeight, 110, totalPlotHeight);
        graphics.drawLine(90, partialPlotHeight, 110, partialPlotHeight);

        Font font = new Font("Verdana", Font.BOLD, 25);
        graphics.setFont(font);
        graphics.drawString(Integer.toString(totalNumber), 40, totalPlotHeight + 13);
        graphics.drawString(Integer.toString(partialNumber), 40, partialPlotHeight + 13);

        graphics.setColor(colorTotalPlot);
        graphics.fillRect(200, totalPlotHeight, 150, 503);
        graphics.fillRect(100, height - 200, 50, 50);
        graphics.drawString("Total recordings", 200, height - 150);

        graphics.setColor(colorPartialPlot);
        graphics.fillRect(400, partialPlotHeight, 150, proportionalHeight);
        graphics.fillRect(100, height - 100, 50, 50);
        graphics.drawString("Partial recordings wih some properties", 200, height - 50);

        repaint();

        String path = "image.jpg";
        try {
            File outputFile = new File(path);
            ImageIO.write(this.image, "png", outputFile);
        } catch (Exception e) {
            return "ERROR!Something is wrong with saving file! TRY AGAIN!";
        }
        return path;
    }


}
