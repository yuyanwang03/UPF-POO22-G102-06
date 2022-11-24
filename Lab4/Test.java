import javax.swing.JPanel;

import java.awt.*;
import java.lang.annotation.Target;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Test extends JFrame{
    public Test(){
        JFrame frame = new JFrame("Lab 4");
		frame.setPreferredSize(new Dimension(800, 600));
        frame.getContentPane().add(new ImagePanel("Lab4/test.jpg", true));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }

    public static void main(String[] args) {
        /*
        Vector v = new Vector(3);
        v.set(0,1);
        v.set(1,2);
        v.set(2,3);
        v.print();
        v.multiply(3);
        v.print();
        v.zero();
        v.print();

        Matrix m = new Matrix(2,2);
        m.set(0,0,1);
        m.set(0,1,0);
        m.set(1,0,0);
        m.set(1,1,1);
        m.print();
        m.multiply(2);
        m.print();
        m.zero();
        m.print();
        */

        Vector v = new Vector(3);
        v.set3D(1,0,0);

        Matrix m = new Matrix(3,3);
        m.create3DRotationMat(Math.PI / 2);
        System.out.print("rotated matrix:\n");
        m.print();

        System.out.print("vector:\n");
        v.print();
        v.multiplyMat(m);
        System.out.print("multiplication:\n");
        v.print();

        // Vector v2 = new Vector(2);
        // v2.multiplyMat(m);

        Test t = new Test();
        
    }
}

   
