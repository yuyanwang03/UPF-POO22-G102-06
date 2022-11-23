import javax.swing.JPanel;

import java.awt.*;
import java.lang.annotation.Target;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    BufferedImage image;
    
    public ImagePanel(String path){
        try{
            File f = new File(path);
            image = ImageIO.read(f);
            System.out.println("Does file exist? " + f.exists());
            System.out.print("Is image null? ");
            System.out.println(image==null);
        } catch (IOException e) {System.out.println("Failed to load image");}

        // ingore the path, here we are just creating an image of a single color
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        Color rgb = new Color(200, 70, 30);
        graphics.setColor(rgb);
        graphics.fillRect( 0, 0, image.getWidth(), image.getHeight());
    }

    public void paintComponent(Graphics g){
        g.drawImage(image, 150, 30, this);
    }

}
