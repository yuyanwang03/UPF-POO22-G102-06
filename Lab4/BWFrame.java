public class BWFrame extends Frame{
    public BWFrame(int r, int c){
        super(r, c);
    }

    // Set the rgb with one given int value
    public void set(int i, int j, int v){
        double value = oneRGBtoVal(v);
        super.set(i, j, value);
    }

    // Get the rgb value (only one) of a given position
    public int getBW(int i, int j){
        return valToOneRGB(super.get(i, j));
    }

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
}
