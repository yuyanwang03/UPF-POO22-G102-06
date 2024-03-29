public class ColorFrame extends Frame{
    public ColorFrame(int r, int c){
        super(r, c);
    }

    private int[] valToRGB(double rgb){
        int[] ret = new int[3];
        ret[0] = ((int)rgb >> 16) & 255;
        ret[1] = ((int)rgb >> 8) & 255;
        ret[2] = ((int)rgb) & 255;
        return ret;
    }

    private double RGBToVal(int r, int g, int b) {
        double ret = (r << 16) | (g << 8) | b ;
        return ret;
    }

    public void set(int i, int j, int r, int g, int b){
        double ret = RGBToVal(r, g, b);
        set( i, j, ret);
    }

    public int[] getRGB(int i, int j){
        double temp = super.get(i, j);
        int[] ret = valToRGB(temp);
        return ret;
    }

    public void changeBrightness(double delta){
        // delta has to be a positive number because RGB values range from 0-255
        if (delta<0) {return;}
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int[] rgb = valToRGB(get(i, j));
                for (int k = 0; k<rgb.length; k++){
                    // Take the minimun value of the mult and 255
                    rgb[k] = Math.min((int)(delta*rgb[k]), 255);
                }
                // Set the modified RGB
                this.set(i, j, RGBToVal(rgb[0], rgb[1], rgb[2]));
            }
        }
    }

    public void changeRGB(int dR, int dG, int dB){
        // Iterate through all values and change their RGB
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int[] rgb = valToRGB(get(i, j));
                // Make sure that the result does not surpass the value of 255 and that they are positive
                rgb[0] = Math.abs(Math.min((rgb[0]+dR), 255));
                rgb[1] = Math.abs(Math.min((rgb[1]+dG), 255));
                rgb[2] = Math.abs(Math.min((rgb[2]+dB), 255));
                // Set the modified RGB
                this.set(i, j, RGBToVal(rgb[0], rgb[1], rgb[2]));
            }
        }
    }
    
}
