import java.awt.*;
import javax.swing.*;

public class Test{
    private int defaultWidth;
    private int defaultHeight;
    private JFrame frame;
    private ImagePanel imagePanel;

    public Test(String name){
        this.defaultWidth = 800;
        this.defaultHeight = 600;
        imagePanel = null;
        this.frame = new JFrame(name);
		frame.setPreferredSize(new Dimension(this.defaultWidth, this.defaultHeight));
        this.display();
    }
    
    public void display(){
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }

    public void addImageToWindow(String path, Boolean colored){
        deleteImage();
        this.imagePanel = new ImagePanel(path, colored);
        this.frame.getContentPane().add(imagePanel);
        this.display();
    }

    public void addImageToWindow(ColorFrame cf){
        deleteImage();
        this.imagePanel = new ImagePanel(cf);
        this.frame.getContentPane().add(imagePanel);
        this.display();
    }

    public void deleteImage(){
        if (imagePanel!=null) {
            System.out.print("Deleting previous image...");
            this.frame.remove(this.imagePanel);
            System.out.println(" Success! \n");
        }
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }

    public void changeBrightness(double delta){
        System.out.print("Program is changing the brigthness of the image...");
        this.imagePanel.changeBrightness(delta);
        this.display();
        System.out.println(" Success! \n");
    }

    public ImagePanel getImagePanel(){ return this.imagePanel;}

    public static void main(String[] args) {
        System.out.println("Starting to execute the program...");
        // Vector v = new Vector(3);
        // v.set(0,1);
        // v.set(1,2);
        // v.set(2,3);
        // v.print();
        // v.multiply(3);
        // v.print();
        // v.zero();
        // v.print();

        // Matrix m = new Matrix(2,2);
        // m.set(0,0,1);
        // m.set(0,1,0);
        // m.set(1,0,0);
        // m.set(1,1,1);
        // m.print();
        // m.multiply(2);
        // m.print();
        // m.zero();
        // m.print();

        // Vector v = new Vector(3);
        // v.set3D(1,0,0);

        // Matrix m = new Matrix(3,3);
        // m.create3DRotationMat(Math.PI / 2);
        // System.out.print("rotated matrix:\n");
        // m.print();

        // System.out.print("vector:\n");
        // v.print();
        // v.multiplyMat(m);
        // System.out.print("multiplication:\n");
        // v.print();

        // Vector v2 = new Vector(2);
        // v2.multiplyMat(m);

        Test windows = new Test("windows 1");
        // windows.addImageToWindow("Lab4/pic2.jpg");
        
        windows.addImageToWindow("Lab4/pic1.jpg", false);
        // ColorFrame cf1 = windows.getImagePanel().toColorFrame();
        // windows.deleteImage();
        // Test windows2 = new Test("windows 2");
        // windows2.addImageToWindow(cf1);
        windows.changeBrightness(2);
    }
}

   
