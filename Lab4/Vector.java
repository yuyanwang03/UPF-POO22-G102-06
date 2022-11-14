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
            System.out.print(values[i]+" ");
        }
    }

    public void set3D(double i, double j, double k) {
        this.dim = 3;
        values = new double[dim];
        values[0] = i;
        values[1] = j;
        values[2] = k;
    }

    public void multiplyMat(Matrix m) {
        double[] temp = new double[3];
        for (int i=0; i < m.getNRows(); i++){
            temp[i] = m.get(i,0) * values[0] + m.get(i,1) * values[1] + m.get(i,2) * values[2]; 
        }
        this.set3D(temp[0], temp[1], temp[2]);
    }
}
