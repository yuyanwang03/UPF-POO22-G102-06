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

    public int getNCols(){return this.nCols;}
    public int getNRows(){return this.nRows;}

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
        }
        // System.out.print("\n");
    }

    public void create3DRotationMat(double alpha){
        this.values = new Vector[3];
        this.nRows = 3;
        this.nCols = 3;
        for (int i = 0; i<3; i++){
            values[i] = new Vector(3);
        }
        System.out.println("cos "+ Math.cos(alpha));
        System.out.println("sin "+Math.sin(alpha));
        values[0].set3D(Math.cos(alpha), -Math.sin(alpha), 0);
        values[1].set3D(Math.sin(alpha), Math.cos(alpha), 0);
        values[2].set3D(0, 0, 1);

        this.print();
    }

}
