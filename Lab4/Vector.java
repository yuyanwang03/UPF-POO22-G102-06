import java.util.*;

public class Vector {
    private double[] values;
    private int dim;

    public Vector(int d) {
        this.dim = d;
        this.values = new double[dim]; 
    }

    public void set(int i, double val) {
        values[i] = val;
    }

    public double get(int i) {
        return values[i];
    }

    public void zero() {
        for (int i=0; i<dim; i++){
            values[i] = 0;
        }
        
    }

    public void multiply(double s) {
        for (int i=0; i<dim; i++){
            values[i] = values[i] * s; 
        }
    }

    public void print() {
        for (int i=0; i<dim; i++){
            System.out.print(values[i]);
        }
    }

}
