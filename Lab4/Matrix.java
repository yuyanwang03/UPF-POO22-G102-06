import java.util.Vector;

public class Matrix {
    private Vector[] values;
    private int nRows;
    private int nCols;

    public Matrix(int r, int c){
        this.nRows = r;
        this.nCols = c;
        this.values = new Vector[r];
        for (int i = 0; i<r; i++){
            values[i] = new Vector(c);
        }
    }

    public void set(int i, int j, double val){
        values[i].set(j, val);
    }

    public double get(int i, int j){
        return values[i].get(j);
    }

    public void multiply(double s){
        for (int i = 0; i<nRows; i++){
            values[i].multiply(s);
        }
    }

    public void zero(){
        for (int i = 0; i<nRows; i++){
            values[i].zero();
        }
    }

    public void print(){
        for (int i = 0; i<nRows; i++){
            values[i].print();
            System.out.print("\n");
        }
    }

}
