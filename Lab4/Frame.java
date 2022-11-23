public abstract class Frame extends Matrix{
    public Frame(int r, int c){
        super(r, c);
    }

    public abstract void changeBrightness(double delta);
   
}
