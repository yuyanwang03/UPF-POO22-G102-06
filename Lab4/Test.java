public class Test {
    public static void main(String[] args) {
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
    }
}

   
