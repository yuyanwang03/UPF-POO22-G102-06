public class BWFrame extends Frame{
    public BWFrame(int r, int c){
        super(r, c);
    }

    public void set(int i, int j, double v){
        super.set(i, j, v);
    }

    public int getBW(int i, int j){
        return (int)super.get(i, j);
    }

    public void changeBrightness(double delta){
        this.multiply(delta);
    }
}
