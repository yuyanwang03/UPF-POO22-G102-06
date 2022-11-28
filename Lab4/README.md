# Lab 4 report

## 1. Introduction

<<<<<<< HEAD
=======
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


Finally we need to include one more method in the ColorFrame class which is changeRGB to increase or decrease the RGB components of an image
 
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

## 3. Conclusion
>>>>>>> 74927813a4a9b8790742e801257105d3f3e2ed45
