public class AudioBuffer extends Vector{
    public AudioBuffer(int d){
        super(d);
    }

    public void changeVolume(double delta){
        this.multiply(delta);
    }
    
}
