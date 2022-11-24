import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test extends JFrame implements ActionListener{
    private int defaultWidth;
    private int defaultHeight;
    private ImagePanel imagePanel;
    JButton increaseB;
    JButton decreaseB;

    public Test(String name){
        // Construct empty window with a given name
        this.setTitle(name);
        this.defaultWidth = 900;
        this.defaultHeight = 750;
        imagePanel = null;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 800, 40);
        increaseB = new JButton("Click to Increase Brightness by 5%");
        decreaseB = new JButton("Click to Decrease Brightness by 5%");

        increaseB.addActionListener(this);
        decreaseB.addActionListener(this);

        buttonPanel.add(increaseB);
        buttonPanel.add(decreaseB);

        increaseB.setBounds(0, 0, 60, 20);
        decreaseB.setBounds(100, 0, 60, 20);
        // this.frame = new JFrame(name);
        this.add(buttonPanel);
		this.setPreferredSize(new Dimension(this.defaultWidth, this.defaultHeight));
        this.setLocation(400, 200);
        this.display();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (this.imagePanel==null){
            System.out.println("You actually do not have any image! The program can not do any action");
            return;
        }
        if (e.getSource()==increaseB){
            this.changeBrightness(1.05);
            JDialog d = new JDialog(this, "message from the program");
            // create a label
            JLabel l = new JLabel("You have succesfully incremented the brightness of the image!");
            l.setHorizontalAlignment(JLabel.CENTER);
            d.add(l);
            // setsize of dialog
            d.setSize(450, 120);
            d.setLocation(500, 100);
            // set visibility of dialog
            d.setVisible(true);

        } else if (e.getSource()==decreaseB){
            this.changeBrightness(0.95);
            JDialog d = new JDialog(this, "message from the program");
            // create a label
            JLabel l = new JLabel("You have successfully decreased the brightness of the image!");
            l.setHorizontalAlignment(JLabel.CENTER);
            d.add(l);
            // setsize of dialog
            d.setSize(450, 120);
            d.setLocation(500, 100);
            // set visibility of dialog
            d.setVisible(true);
        }
    }

    public void display(){
        // Display the frame
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    public void addImageToWindow(String path, Boolean colored){
        // Each Window is only capable of showing one image
        // Delete (if there exists) the previous image
        deleteImage();
        // Add ImagePanel to the attribute
        this.imagePanel = new ImagePanel(path, colored);
        // Add the panel to the frame
        this.getContentPane().add(imagePanel);
        // Display the changes
        this.display();
    }

    // Overloading the previous method to be able to create an ImagePanel from a given Frame (matrix)
    public void addImageToWindow(Frame fr){
        deleteImage();
        this.imagePanel = new ImagePanel(fr);
        this.getContentPane().add(imagePanel);
        this.display();
    }

    // Delete stored ImagePanel attribute
    public void deleteImage(){
        if (imagePanel!=null) {
            System.out.print("Deleting previous image... ");
            // Do this only if the imagePanel is not null
            this.remove(this.imagePanel);
            this.imagePanel = null;
            System.out.println("Success!\n");
        }
        this.display();
    }

    public void changeBrightness(double delta){
        // Change brigthness of the only displayed image
        System.out.print("Program is changing the brightness of the image... ");
        this.imagePanel.changeBrightness(delta);
        this.display();
        System.out.println("Success!\n");
    }

    public void changeRGB(int r, int g, int b){
        // Change RGB only if the ImagePanel has a ColorFrame (= the image is considered a colored image)
        System.out.print("The program is changing the rgb of the picture... ");
        Boolean success = this.imagePanel.changeRGB(r, g, b);
        if (success == true){
            this.display();
            System.out.println("Success!\n");
        } else {System.out.println("You cannot change rgb of a BW picture\n");}
       
    }

    public ImagePanel getImagePanel(){ return this.imagePanel;}

    public static void main(String[] args) {
        System.out.println("Starting to execute the program... ");
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

        Test windows = new Test("Test 1");
        // windows.addImageToWindow("Lab4/pic2.jpg");
        
        windows.addImageToWindow("Lab4/pic1.jpg", true);
        // ColorFrame cf1 = windows.getImagePanel().toColorFrame();
        // windows.deleteImage();
        // Test windows2 = new Test("windows 2");
        // windows2.addImageToWindow(cf1);
        windows.changeBrightness(1.1);
        // windows.changeRGB(100, 70, -80);
    }
}

   
