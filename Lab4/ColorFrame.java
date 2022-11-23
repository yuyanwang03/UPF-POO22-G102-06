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
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                // Get RGB of each value in the matrix
                int[] rgb = valToRGB(get(i, j));
                // Check that the multiplication does not surpass the value of 255
                for (int k = 0; k<rgb.length; k++){
                    // If the value of the mult surpasses 255, skip that procedure and leave the value as it was
                    if (rgb[k]*delta>255){continue;}
                    rgb[k] *= delta;
                }
                // Set the modified RGB
                this.set(i, j, RGBToVal(rgb[0], rgb[1], rgb[2]));
            }
        }
    }

    public void changeRGB(int dR, int dG, int dB){
        // Avoid that the values of RGB are greater than 255, as the exercise states
        if (dR > 255 || dG > 255 || dB > 255) {
            System.out.println("Each value of RGB cannot be higher than 255, the changeRGB method is skipped");
            return;
        }
        // Iterate through all values and change their RGB
        for (int i = 0; i < this.getNRows(); i++) {
            for (int j = 0; j < this.getNCols(); j++) {
                this.set(i, j, RGBToVal(dR, dG, dB));
            }
        }
    }
    
}
