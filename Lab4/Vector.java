public class Vector {
    private double[] values;
    private int dim;

    public Vector(int d) {
        this.dim = d;
        this.values = new double[dim]; 
    }

    public void set(int i, double val) {values[i] = val;}

    public double get(int i) {return values[i];}

    public void zero() {
        for (int i=0; i<dim; i++){
            values[i] = 0;
        }
    }

    public void multiply(double s) {
        for (int i=0; i<dim; i++) {values[i] *= s;}
    }

    public void print() {
        for (int i=0; i<dim; i++){
            System.out.printf("%10.2f", values[i]);
        }
        System.out.print("\n");
    }

    public void set3D(double i, double j, double k) {
        // Force the vector to be 3D
        this.dim = 3;
        values = new double[dim];
        // Set values
        this.set(0, i);
        this.set(1, j);
        this.set(2, k);
    }

    private double[] getValues(){ return this.values;}

    public void multiplyMat(Matrix m) {
        // Raise Error if dimensions does not match, so from a mathematical point of view the multiplication cannot be done
        if (this.dim != m.getNCols()){ throw new java.lang.Error("Error in the dimensions of vector and matrix; multiplication cannot be proceeded");}

        // Make a temporal Vector to store the result of the multiplication
        Vector temp = new Vector(this.dim);

        for (int i = 0; i < m.getNRows(); i++) {
            double sum = 0;
            for (int j = 0; j < m.getNCols(); j++) {
                sum += m.get(i, j) * this.get(j);
            }
            temp.set(i, sum);
        }
        
        // Set the resulted values to the current vector
        this.values = temp.getValues();
    }
}
