import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    // private Frame frame;
    
    public ImagePanel(String path, Boolean colored){
        try{
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            this.image = ImageIO.read(new File(path));
            // if (!colored) {this.frame = this.toBWFrame();}
            // else {this.frame = this.toBWFrame();}
        } catch (IOException e) {System.out.println("Failed to load image, check file paths\n");}
    }

    // Overloading the method to be able to instantiate an ImagePanel from a given Colorframe matrix
    public ImagePanel(ColorFrame cf){
        this.image = new BufferedImage(cf.getNRows(), cf.getNCols(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < cf.getNCols(); y++) {
            for (int x = 0; x < cf.getNRows(); x++) {
                this.image.setRGB(x, y, (int)cf.get(x, y));
            }
        }
    }

    public void paintComponent(Graphics g){
        // Draw the component with a fixed size (800, 600), which is exactly the size of the windows we are creating
        g.drawImage(image, 0,0, 800, 600, this);
    }

    public ColorFrame toColorFrame(){
        int height = image.getHeight(), width = image.getWidth();
        ColorFrame out = new ColorFrame(image.getWidth(), image.getHeight());
        System.out.println("The program is loading each image pixel into a matrix, please be patient, the process may take few seconds...\n");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                out.set(x, y, rgb);
            }
        }
        System.out.println("Image sucessfully read\n");

        // this.frame = out;
        return out;
    }

    public BWFrame toBWFrame(){
        BWFrame out = new BWFrame(100, 100);
        return out;
    }

    public void fromBWFrame(BWFrame bwf){
        
    }
}
