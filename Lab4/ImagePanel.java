import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private Frame frame;
    
    public ImagePanel(String path, Boolean colored){
        try{
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            this.image = ImageIO.read(new File(path));
            if (!colored) {
                BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
                Graphics2D graphic = temp.createGraphics();
                graphic.drawImage(image, 0, 0, Color.WHITE, null);
                graphic.dispose();
                this.image = temp;
                this.frame = this.toBWFrame();
            }
            else {this.frame = this.toColorFrame();}
        } catch (IOException e) {System.out.println("Failed to load image, check file paths\n");}
    }

    // Overloading the method to be able to instantiate an ImagePanel from a given Frame (matrix)
    // This method is not really necessary, it is here just to test the code
    public ImagePanel(Frame fr){
        this.image = fromFrame(fr);
        this.frame = fr;
    }

    public void paintComponent(Graphics g){
        // Draw the component with a fixed size (800, 600), which is exactly the size of the windows we are creating
        g.drawImage(image, 0,0, 800, 600, null);
        g.dispose();
    }

    private ColorFrame toColorFrame(){
        int height = image.getHeight(), width = image.getWidth();
        ColorFrame out = new ColorFrame(image.getWidth(), image.getHeight());
        System.out.print("The program is loading each image pixel into a matrix, please be patient, the process may take few seconds... ");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                out.set(x, y, rgb);
            }
        }
        System.out.println("Success! \n");
        return out;
    }

    private BWFrame toBWFrame(){
        BWFrame out = new BWFrame(image.getWidth(), image.getHeight());
        int height = image.getHeight(), width = image.getWidth();
        System.out.print("The program is loading each image pixel into a matrix, please be patient, the process may take few seconds... ");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                out.set(x, y, rgb);
            }
        }
        System.out.println("Success! \n");
        return out;
    }

    private BufferedImage fromFrame(Frame fr){
        BufferedImage out = new BufferedImage(fr.getNRows(), fr.getNCols(), BufferedImage.TYPE_INT_RGB);
        if (fr instanceof BWFrame) {out = new BufferedImage(fr.getNRows(), fr.getNCols(), BufferedImage.TYPE_BYTE_BINARY);}
        for (int y = 0; y < fr.getNCols(); y++) {
            for (int x = 0; x < fr.getNRows(); x++) {
                out.setRGB(x, y, (int)fr.get(x, y));
            }
        }
        return out;
    }

    public void changeBrightness(double delta){
        this.frame.changeBrightness(delta);
        this.image = fromFrame(this.frame);
    }

    public Boolean changeRGB(int r, int g, int b){
        if (this.frame instanceof BWFrame) {return false;}
        ((ColorFrame)this.frame).changeRGB(r, g, b);
        this.image = fromFrame(this.frame);
        return true;
    }
}
