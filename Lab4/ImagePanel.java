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
            if (!colored){
                // Set the type of the BufferedImage as TYPE_BYTE_BINARY if the boolean is false
                BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
                Graphics2D graphic = temp.createGraphics();
                graphic.drawImage(image, 0, 0, Color.WHITE, null);
                graphic.dispose();
                this.image = temp;
                // Create BWFrame
                this.frame = this.toBWFrame();
            } else{
                // Create ColorFrame
                this.frame = this.toColorFrame();
            }
            paintComponent(image.getGraphics());
            this.setBounds(50, 5, 800, 600);
        } catch (IOException e) {System.out.println("Failed to load image, check file paths\n");}
    }

    // Overloading the method to be able to instantiate an ImagePanel from a given Frame (matrix)
    // This method is not really necessary, it is here just to test the code
    public ImagePanel(Frame fr){
        this.image = fromFrame(fr);
        this.frame = fr;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Draw the component with a fixed size (800, 600), which is exactly the size of the windows we are creating
        g.drawImage(image, 50,50, 800, 600, this);
        g.dispose();
    }

    // Load a ColorFrame by reading each pixel of the BufferedImage
    private ColorFrame toColorFrame(){
        int height = image.getHeight(), width = image.getWidth();
        ColorFrame out = new ColorFrame(image.getWidth(), image.getHeight());
        System.out.print("The program is loading each image pixel into a matrix, please be patient, the process may take few seconds... ");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the rgb value of the pixel at location (x, y)
                int rgb = image.getRGB(x, y);
                // Save the RGB value inside the output matrix (ColorFrame)
                out.set(x, y, rgb);
            }
        }
        System.out.println("Success! \n");
        return out;
    }

    // Load a BWFrame by reading each pixel of the BufferedImage
    private BWFrame toBWFrame(){
        BWFrame out = new BWFrame(image.getWidth(), image.getHeight());
        int height = image.getHeight(), width = image.getWidth();
        System.out.print("The program is loading each image pixel into a matrix, please be patient, the process may take few seconds... ");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the rgb value of the pixel at location (x, y)
                int rgb = image.getRGB(x, y);
                // Save the RGB value inside the output matrix (ColorFrame)
                out.set(x, y, rgb);
            }
        }
        System.out.println("Success! \n");
        return out;
    }

    // Create a BufferedImage from a given Frame (matrix)
    private BufferedImage fromFrame(Frame fr){
        // Instantiate a BufferedImage of type TYPE_INT_RGB if the given frame is not an instance of BWFrame
        BufferedImage out = new BufferedImage(fr.getNRows(), fr.getNCols(), BufferedImage.TYPE_INT_RGB);
        if (fr instanceof BWFrame) {out = new BufferedImage(fr.getNRows(), fr.getNCols(), BufferedImage.TYPE_BYTE_BINARY);}
        // Fill every pixel of the image with the values of the given Frame
        for (int y = 0; y < fr.getNCols(); y++) {
            for (int x = 0; x < fr.getNRows(); x++) {
                out.setRGB(x, y, (int)fr.get(x, y));
            }
        }
        return out;
    }
    
    // Call diferent implementations of change brightness depending on the type of the frame (if it is ColorFrame or BWFrame)
    public void changeBrightness(double delta){
        // Create a frame (matrix) with the modified values for each pixel
        this.frame.changeBrightness(delta);
        // Redo the image from the modified Frame
        this.image = fromFrame(this.frame);
        this.repaint();
    }

    public Boolean changeRGB(int r, int g, int b){
        // Exit the program if the Frame is a BWFrame
        if (this.frame instanceof BWFrame) {return false;}
        // Downcast this.frame because up to this line we can ensure that it is of ColorFrame type and changeRGB()
        ((ColorFrame)this.frame).changeRGB(r, g, b);
        // Redo the image from the modified Frame
        this.image = fromFrame(this.frame);
        this.repaint();
        return true;
    }
}
