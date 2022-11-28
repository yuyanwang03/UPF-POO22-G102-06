# Lab 4 report

## 1. Introduction

The aim of this report is to describe how we have done lab 4, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement the design of seminar 4 emphasising the use of inheritance and code reuse. Unlike the other labs, we were not given any existing classes. We created 6 classes which are Vector, Matrix, AudioBuffer, Frame, BWFrame, ColorFrame, then the ImagePanel for the optional part and finally a Test class including the main method

## 2. Implementation

In the explanation of the implementation of this Lab3, we will not go through all the existing classes/methods because some of them are just to simple and are not worth-mentioning. We will just mention some of the essential aspects of the code.

The first two classes we need to implement are Vector and Matrix. These two classes are for general use, which means we can reuse them in future labs if needed. The steps to create these two classes are pretty much similar to what we did in lab 1, and since there are only mathematical structures, we are not going deep into explaining this part. The only thing that needs to be kept in mind is the composition relation between these two: a matrix has arrays of vectors. Then, for the multiplyMatrix function in Vector class, we need to make sure their dimensions match. After the implementation, we include the test in the main method. This is the result we obtain:

![image](https://user-images.githubusercontent.com/92045687/204167283-f6395ab1-3166-47fe-a1c6-979e2dfefa3c.png)


### ***AudioBuffer class***

Following the seminar design, this class inherits from Vector class. It only has two methods, which are the constructor and changeVolume

```java
public class AudioBuffer extends Vector{
    public AudioBuffer(int d){
        super(d);
    }
 
    public void changeVolume(double delta){
        this.multiply(delta);
    }
   
}
``` 

### ***Frame class***

Similar to AudioBuffer class, this class inherits from Matrix class. Moreover, it is an abstract class that gathers the common operations of BWFrame and ColorFrame, which in our case is the abstract method changeBrightness. Since abstract methods can only be declared without implementation, we will continue with its two subclasses BWFrame and ColorFrame

```java
public abstract class Frame extends Matrix{
    public Frame(int r, int c){
        super(r, c);
    }
 
    public abstract void changeBrightness(double delta);
   
}
```

### ***BWFrame and ColorFrame classes***

Both classes have setter and getter methods. While a pixel of a black and white image only takes one integer value from range 0 to 255, a pixel of a color image needs 3 integers indicating its RGB values. Since the getters in these two classes have different return types, we cannot override. Therefore we change their names to getRGB and getBW. Before implementing these methods, we would need to have the functions translating a double value into three RGB components and vice versa: valToRGB, RGBToVal. We also do the same for BW values

(We are guided for the ColorFrame methods thus we are only explaining the similar methods for BWFrame)

```java
// Get one of the RGB values from one given rgb value
    private int valToOneRGB(double rgb){
        // For gray colored colors, the 3 rgb values are the same and thus we only need to take one value to know all rgb
        int ret = ((int)rgb >> 16) & 255;
        return ret;
    }
 
    // Get the rgb value from a given value
    private double oneRGBtoVal(int v){
        // For gray colored colors, the 3 rgb values are the same and thus we can create a gray colo from one given value
        double ret = (v << 16) | (v << 8) | v ;
        return ret;
    }
```

The abstract changeBrightness method will be implemented in these two classes. 

***ColorFrame:***

```java
public void changeBrightness(double delta){
        // delta has to be a positive number because RGB values range from 0-255
        if (delta<0) {return;}
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int[] rgb = valToRGB(get(i, j));
                for (int k = 0; k<rgb.length; k++){
                    // Take the minimun value of the mult and 255
                    rgb[k] = Math.min((int)(delta*rgb[k]), 255);
                }
                // Set the modified RGB
                this.set(i, j, RGBToVal(rgb[0], rgb[1], rgb[2]));
            }
        }
    }
```

***BWFrame:***

```java
public void changeBrightness(double delta){
        // delta has to be a positive number because RGB values range from 0-255
        if (delta<0) {return;}
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int rgb = valToOneRGB(get(i, j));
                // Take the minimun value of the mult and 255
                int newRGB = Math.min((int)(rgb*delta), 255);
                // Set the modified RGB
                this.set(i, j, newRGB);
            }
        }
    }
```


Finally we need to include one more method in the ColorFrame class which is changeRGB to increase or decrease the RGB components of an image.
 
 ```java
public void changeRGB(int dR, int dG, int dB){
        // Iterate through all values and change their RGB
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int[] rgb = valToRGB(get(i, j));
                // Make sure that the result does not surpass the value of 255 and that they are positive
                rgb[0] = Math.abs(Math.min((rgb[0]+dR), 255));
                rgb[1] = Math.abs(Math.min((rgb[1]+dG), 255));
                rgb[2] = Math.abs(Math.min((rgb[2]+dB), 255));
                // Set the modified RGB
                this.set(i, j, RGBToVal(rgb[0], rgb[1], rgb[2]));
            }
        }
    }
```

### ***Test class***

We have implemented also the optional part, so that is why the Test Class is way more complex than expected. It has as attributes components of java swing and requires some knowledge of java ui. The only thing we need to remark here is that it contains an attribute that is an ImagePanel, which would be used to draw a single image on the screen. That is, each windows is allowed to save the instance of only one ImagePanel.

Its contructor method will create a new windows in the computer and draw the default buttons and selection panels to let the user do whatever he/she is allowed to do. 

```java
public Test(String name){
    // Construct empty window with a given name
    this.setTitle(name);
    // Set attribute values
    this.defaultWidth = 900;
    this.defaultHeight = 850;
    imagePanel = null;
    // Set empty layout
    this.setLayout(null);
    // Add panels to the frame
    this.add(createSelectImagePanel(5, 0));
    this.add(createBrightnessPanel(0, 700));
    this.add(createRGBPanel(0, 760));
    // Display the whole frame
    this.setPreferredSize(new Dimension(this.defaultWidth, this.defaultHeight));
    this.setLocation(400, 180);
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
}
```

The design of the windows is defined by following methods:

```java
private JPanel createSelectImagePanel(int x, int y){
    // Create the selectImagePanel
    JPanel selectImagePanel = new JPanel();
    selectImagePanel.setBounds(x, y, this.defaultWidth, 60);
    selectImagePanel.setLayout(null);
    // Create options, combo box, button and botton group
    String[] imageOptions = {"Select image to work with...", "pic1.jpg", "pic2.jpg", "pic3.jpg", "pic4.jpg"};
    selectImage = new JComboBox<String>(imageOptions);
    selectImage.setBounds(100, 15, 230, 30);
    colorImage = new JRadioButton("Colored");
    colorImage.setBounds(380, 10, 150, 20);
    bwImage = new JRadioButton("Black and White");
    bwImage.setBounds(380, 30, 150, 20);
    // Button group allows only one option to be selected
    imageColor = new ButtonGroup();
    imageColor.add(colorImage);
    imageColor.add(bwImage);
    loadImage = new JButton("Load selected image");
    loadImage.setBounds(580, 15, 200, 30);
    // Create action listeners
    selectImage.addActionListener(this);
    colorImage.addActionListener(this);
    bwImage.addActionListener(this);
    loadImage.addActionListener(this);
    // Add combo combo box, button and botton group to the panel
    selectImagePanel.add(selectImage);
    selectImagePanel.add(colorImage);
    selectImagePanel.add(bwImage);
    selectImagePanel.add(loadImage);
    return selectImagePanel;
}

private JPanel createBrightnessPanel(int x, int y){
    // Create the buttonPanel
    JPanel brightnessPanel = new JPanel();
    brightnessPanel.setBounds(x, y, this.defaultWidth, 40);
    brightnessPanel.setLayout(null);
    // Create the buttons
    increaseB = new JButton("Click to Increase Brightness by 5%");
    increaseB.setBounds(100, 5, 300, 30);
    decreaseB = new JButton("Click to Decrease Brightness by 5%");
    decreaseB.setBounds(500, 5, 300, 30);
    // Create action listeners
    increaseB.addActionListener(this);
    decreaseB.addActionListener(this);
    // Add buttons to the panel
    brightnessPanel.add(increaseB);
    brightnessPanel.add(decreaseB);
    return brightnessPanel;
}

private JPanel createRGBPanel(int x, int y){
    // Create the RGBPanel
    JPanel rgbPanel = new JPanel();
    rgbPanel.setBounds(x, y, this.defaultWidth, 40);
    rgbPanel.setLayout(null);
    // Create the button and text fields
    changeRGB = new JButton("Click to Modify RGB values");
    changeRGB.setBounds(500, 5, 300, 30);
    editR = new JTextArea("R");
    editR.setBounds(110, 5, 50, 30);
    editR.setForeground(Color.red);
    editG = new JTextArea("G");
    editG.setBounds(220, 5, 50, 30);
    editG.setForeground(Color.green);
    editB = new JTextArea("B");
    editB.setBounds(340, 5, 50, 30);
    editB.setForeground(Color.cyan);
    // Create action listeners
    changeRGB.addActionListener(this);
    // Add components to the panel
    rgbPanel.add(changeRGB);
    rgbPanel.add(editR);
    rgbPanel.add(editB);
    rgbPanel.add(editG);
    return rgbPanel;
}

private void addImageToWindow(String path, Boolean colored){
    // Each Window is only capable of showing one image
    // Delete (if there exists) the previous image
    deleteImage();
    // Add ImagePanel to the attribute
    this.imagePanel = new ImagePanel(path, colored);
    this.imagePanel.setBounds(50, 75, 800, 600);
    this.imagePanel.setLayout(new BorderLayout());
    // Add ImagePanel to the frame
    this.add(this.imagePanel);
    // Display the changes
    this.repaint();
    printDialogBox("User manual:<br/> <br/>" + 
                    "The image has been successfully loaded. It you don't see the image on the windows, please do not hesitate.<br/><br/>" +
                    "You can do following to see the displayed image: <br/>" + 
                    "(1) Resize the main windows (the one that contains the buttons) <br/>" +
                    "(2) Click on either 'increase brightness' or 'decrease brightness' button once and click another time on the main windows<br/><br/>" + 
                    "If you don't see any change, please rerun the whole program.<br/><br/>" + 
                    "Reminder: Resize any windows if you cannot see any message printed on it.", 860, 500);
}

// Delete stored ImagePanel attribute
private void deleteImage(){
    if (imagePanel!=null) {
        System.out.print("Deleting previous image... ");
        // Do this only if the imagePanel is not null
        this.remove(this.imagePanel);
        this.imagePanel = null;
        System.out.println("Success!\n");
    }
    this.repaint();
}
```

In order to make the UI work (for instance, do something when the user clicks on a button) method named actionPerformed() should be overriden:

```java
@Override
public void actionPerformed(ActionEvent e){
    // Check which button has the user clicked on
    if(e.getSource() == loadImage){
        String selectedImageName = selectImage.getSelectedIt em().toString();
        // Program works only if the user has selected an image and the range of color of the image
        if (selectedImageName.equals("Select image to work with...") || imageColor.getSelection()==null) {
            printDialogBox("You have to select both image and the color range!<br/>" + " The program cannot do any action if not.", 500, 150);
            return;
        }
        printDialogBox("The program is loading the image, the process may take few seconds...", 500, 120);
        // See if the user has selected a color range or a black and white range
        if (bwImage.isSelected()){
            this.addImageToWindow("Lab4/"+selectedImageName, false);
        } else{
            this.addImageToWindow("Lab4/"+selectedImageName, true);
        }
    } else if (e.getSource() == increaseB){
        // Increase brightness by 5%
        this.changeBrightness(1.05);
        // Pop up a success dialog box
        printDialogBox("You have succesfully incremented the brightness of the image!",500, 120);
    } else if (e.getSource()==decreaseB){
        // Decrease brightness by 5%
        this.changeBrightness(0.95);
        // Pop up a success dialog box
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
            changeRGB(r, g, b);
            // Pop up a success dialog box
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
```

An additional function is used to pop up a dialog box to user's computer:

```java
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
    l.setVisible(true);
    // Set size of dialog box
    d.setSize(width, height);
    // Set location of the diagol box on screen
    d.setLocation(500, 100);
    // Set visibility of dialog
    d.setVisible(true);
}
```

And surely, to implement the main purpose of the program (modify the images), we just need to call the corresponding methods.

```java
private void changeBrightness(double delta){
    // Change brigthness of the only displayed image
    System.out.print("Program is changing the brightness of the image... ");
    this.imagePanel.changeBrightness(delta);
    this.repaint();
    System.out.println("Success!\n");
}

private void changeRGB(int r, int g, int b){
    // Change RGB only if the ImagePanel has a ColorFrame (= the image is considered a colored image)
    System.out.print("The program is changing the rgb of the picture... ");
    Boolean success = this.imagePanel.changeRGB(r, g, b);
    if (success == true){
        this.repaint();
        System.out.println("Success!\n");
    } else {System.out.println("You cannot change rgb of a BW picture\n");}
}
```

### ***ImagePanel class***

It has as attribute a BufferedImage and a Frame, which is exactly the 2 classes that we will be working on. 

It has 2 constuctor methods, one to initialize it with the path od the image and another one to initialize it with the Frame (matrix). The former constructor will also require a boolean because the BufferedImage has diferent types where it can be specified if the image is black and white or or colors. If it were of black and white, the Graphics to draw it should be different. The latter one is there with the purpose of testing the code.

```java
public ImagePanel(String path, Boolean colored){
    try{
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        this.image = ImageIO.read(new File(path));
        if (!colored){
            // Set the type of the BufferedImage as TYPE_BYTE_GRAY if the boolean is false
            BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
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
    } catch (IOException e) {System.out.println("Failed to load image, check file paths\n");}
}

// Overloading the method to be able to instantiate an ImagePanel from a given Frame (matrix)
// This method is not really necessary, it is here just to test the code
public ImagePanel(Frame fr){
    this.image = fromFrame(fr);
    this.frame = fr;
}
```

From the knowledge of java swing, we know that we need to override the method called paint component, because if not, the panel will not be drawn in JFrame.

```java
public void paintComponent(Graphics g){
    super.paintComponent(g);
    // Draw the component with a fixed size (800, 600), which is exactly the size of the imagePanel we are creating
    g.drawImage(image, 0,0, 800, 600, this);
    g.dispose();
}
```

The most important relation between BufferedImage class and the classes we have defined should be the conversion from one to another, defined as following:

```java
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
    if (fr instanceof BWFrame) {out = new BufferedImage(fr.getNRows(), fr.getNCols(), BufferedImage.TYPE_BYTE_GRAY);}
    // Fill every pixel of the image with the values of the given Frame
    for (int y = 0; y < fr.getNCols(); y++) {
        for (int x = 0; x < fr.getNRows(); x++) {
            out.setRGB(x, y, (int)fr.get(x, y));
        }
    }
    return out;
}
```

Lastly, we should also implement the methods changeBrightness and changeRGB:

```java
// Call diferent implementations of change brightness depending on the type of the frame (if it is ColorFrame or BWFrame)
public void changeBrightness(double delta){
    // Create a frame (matrix) with the modified values for each pixel
    this.frame.changeBrightness(delta);
    // Redo the image from the modified Frame
    this.image = fromFrame(this.frame);
}

public Boolean changeRGB(int r, int g, int b){
    // Exit the program if the Frame is a BWFrame
    if (this.frame instanceof BWFrame) {return false;}
    // Downcast this.frame because up to this line we can ensure that it is of ColorFrame type and changeRGB()
    ((ColorFrame)this.frame).changeRGB(r, g, b);
    // Redo the image from the modified Frame
    this.image = fromFrame(this.frame);
    return true;
}
```

## 3. Conclusion
