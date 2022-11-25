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
    JButton changeRGB;
    JTextArea editR;
    JTextArea editG;
    JTextArea editB;

    public Test(String name){
        // Construct empty window with a given name
        this.setTitle(name);
        this.defaultWidth = 900;
        this.defaultHeight = 750;
        imagePanel = null;
        this.setLayout(null);
        JPanel buttonPanel = new JPanel();
        JPanel rgbPanel = new JPanel();
        buttonPanel.setBounds(0, 0, 900, 40);
        buttonPanel.setLayout(null);

        rgbPanel.setBounds(0, 40, 900, 40);
        rgbPanel.setLayout(null);
        increaseB = new JButton("Click to Increase Brightness by 5%");
        decreaseB = new JButton("Click to Decrease Brightness by 5%");
        changeRGB = new JButton("Click to Modify RGB values");

        increaseB.addActionListener(this);
        decreaseB.addActionListener(this);
        changeRGB.addActionListener(this);

        editR = new JTextArea("R");
        editR.setBounds(110, 5, 50, 30);
        editG = new JTextArea("G");
        editG.setBounds(220, 5, 50, 30);
        editB = new JTextArea("B");
        editB.setBounds(340, 5, 50, 30);

        buttonPanel.add(increaseB);
        buttonPanel.add(decreaseB);
        rgbPanel.add(changeRGB);
        rgbPanel.add(editR);
        rgbPanel.add(editB);
        rgbPanel.add(editG);

        increaseB.setBounds(100, 5, 300, 30);
        decreaseB.setBounds(450, 5, 300, 30);
        changeRGB.setBounds(450, 5, 300, 30);

        this.add(buttonPanel);
        this.add(rgbPanel);
		this.setPreferredSize(new Dimension(this.defaultWidth, this.defaultHeight));
        this.setLocation(400, 200);
        this.display();
    }

    public void changeBrightness(double delta){
        // Change brigthness of the only displayed image
        System.out.print("Program is changing the brightness of the image... ");
        this.imagePanel.changeBrightness(delta);
        this.repaint();
        System.out.println("Success!\n");
    }

    public void changeRGB(int r, int g, int b){
        // Change RGB only if the ImagePanel has a ColorFrame (= the image is considered a colored image)
        System.out.print("The program is changing the rgb of the picture... ");
        Boolean success = this.imagePanel.changeRGB(r, g, b);
        if (success == true){
            this.repaint();
            System.out.println("Success!\n");
        } else {System.out.println("You cannot change rgb of a BW picture\n");}
       
    }

    public ImagePanel getImagePanel(){ return this.imagePanel;}
    
    @Override
    public void actionPerformed(ActionEvent e){
        // Click on the buttons will not have any effect if there is no image loaded
        if (this.imagePanel==null){
            printDialogBox("You currently do not have any image! The program can not do any action.", 500, 120);
            return;
        }
        // Check with button has the user clicked on
        if (e.getSource() == increaseB){
            // Increase brightness by 5%
            this.changeBrightness(1.05);
            // Pop up a success dialog box
            printDialogBox("You have succesfully incremented the brightness of the image!",500, 120);
        } else if (e.getSource()==decreaseB){
            // Decrease brightness by 5%
            this.changeBrightness(0.95);
            // Pop out a success dialog box
            printDialogBox("You have succesfully decreased the brightness of the image!", 500, 120);
        } else if (e.getSource()==changeRGB){
            // Check if the image is BlackWhite
            if (imagePanel.isBWImage()) {
                printDialogBox("Program cannot access to this functionality because it is a BlackWhite image.", 500, 120);
                // Exit the method without making any change
                return;
            }
            try {
                // Check that the inserted values at the JTextArea are integers (positive or negative)
                int r = Integer.parseInt(editR.getText());
                int g = Integer.parseInt(editG.getText());
                int b = Integer.parseInt(editB.getText());
                // // Pop up a logger message because the program will take a while doing the computation
                // printDialogBox("Program is changing the RGB, the process may take a while... <br/> A message will pop up when the process is finished.", 500, 160);
                changeRGB(r, g, b);
                // Pop out a success dialog box
                printDialogBox("You have succesfully changed the RGB of the image!", 500, 120);
            } catch(NumberFormatException e2){  
                printDialogBox("The values you have inserted for RGB are in a wrong format; program cannot change the RGB of the image.<br/>" + 
                                "You need to fill the 3 textboxes with (+/-) integer values in order to be a valid input.<br/>" + "<br/>Try another time.", 750, 200);
            }
            // Reset the messages at each JTextArea
            editR.setText("R");
            editG.setText("G");
            editB.setText("B");
        }
    }

    // Pop up a dialog box with a given message to show on it
    private void printDialogBox(String message, int width, int height){
        JDialog d = new JDialog(this, "message from the program");
        // Convert given string to html format in order to be able to print new lines. And add a logger message
        String temp = "<html>" + message + "<br/> <br/>" + "This is a logger message, you may close this dialog box :)" + "<br/>" +"</html>";
        // Create a label
        JLabel l = new JLabel(temp);
        // Situate the label at the center of the dialog box
        l.setHorizontalAlignment(JLabel.CENTER);
        d.add(l);
        // Set size of dialog box
        d.setSize(width, height);
        // Set location of the diagol box on screen
        d.setLocation(500, 100);
        // Set visibility of dialog
        d.setVisible(true);
    }

    public void display(){
        // Display the frame
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    public void addImageToWindow(String path, Boolean colored){
        printDialogBox("The program is loading the image, the process may take few seconds...", 500, 120);
        // Each Window is only capable of showing one image
        // Delete (if there exists) the previous image
        deleteImage();
        // Add ImagePanel to the attribute
        this.imagePanel = new ImagePanel(path, colored);
        this.imagePanel.setBounds(50, 100, 800, 600);
        this.imagePanel.setLayout(new BorderLayout());
        // Add ImagePanel to the frame
        this.add(this.imagePanel);
        // Display the changes
        this.repaint();
        printDialogBox("User manual:<br/> <br/>" + "The image has been successfully loaded. It you don't see the image on the windows, please do not hesitate.<br/><br/>" +
                        "You can do following to see the displayed image: <br/>" + "(1) Resize the main windows (the one that contains the buttons) <br/>" +
                        "(2) Click on either 'increase brightness' or 'decrease brightness' button once and click another time on the main windows<br/><br/>" + 
                        "If you don't see any change, please rerun the whole program.<br/><br/>" + "Reminder: Resize any windows if you cannot see any message printed on it.", 860, 500);
    }

    // Overloading the previous method to be able to create an ImagePanel from a given Frame (matrix)
    public void addImageToWindow(Frame fr){
        deleteImage();
        this.imagePanel = new ImagePanel(fr);
        this.getContentPane().add(imagePanel);
        this.repaint();
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
        this.repaint();
    }

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
        // // windows.changeBrightness(1.1);
        // windows.changeRGB(100, 70, -80);
    }
}

   
