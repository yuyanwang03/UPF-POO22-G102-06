import javax.swing.JPanel;

import com.google.zxing.client.j2se.ImageReader;

import java.awt.*;
import java.lang.annotation.Target;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private Frame frame;
    
    public ImagePanel(String path, Boolean colored){
        try{
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            this.image = ImageIO.read(new File(path));
            if (!colored) {this.frame = this.toBWFrame();}
            else {this.frame = this.toBWFrame();}
        } catch (IOException e) {System.out.println("Failed to load image");}
    }

    public void paintComponent(Graphics g){
        g.drawImage(image, 150, 30, this);
    }

    public ColorFrame toColorFrame(){
        ColorFrame out = new ColorFrame(100, 100);
        return out;
    }

    public void fromColorFrame(ColorFrame cf){

    }

    public BWFrame toBWFrame(){
        BWFrame out = new BWFrame(100, 100);
        return out;
    }

    public void fromBWFrame(BWFrame bwf){
        
    }
}
