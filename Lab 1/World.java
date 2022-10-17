import java.awt.*;

public class World {
    private int width;
	private int height;
	private Agent[] agents;
	private static int capacity;
	private int margin;

    World(int initWidth, int initHeight){
        width = initWidth;
		height = initHeight;
		margin = 30;
		capacity = 10;
		agents = new Agent[capacity];
        for (int i=0; i<capacity; i++) {
			agents[i] = new Agent(randomPos(), randomRadius());
			agents[i].setTarget(randomPos());
			agents[i].setSpeed(1);
		}
    }

    private Vec2D randomPos() {
		double x = margin + Math.random()*(width - 2 * margin);
		double y = margin + Math.random()*(height - 2 * margin);
		return new Vec2D( x , y );
	}

    private double randomRadius() {
		return 5 + Math.random() * (margin - 5) ;
	}

    public void simulationStep() {
		for (int i=0; i<capacity; i++) {
			agents[i].updatePosition();
			if (agents[i].reachedTarget() == true) {
				agents[i].setTarget(randomPos());
			}
		}
	}

    public void paint(Graphics g){
        for (int i=0; i<capacity; i++){
            agents[i].paint(g);
        }
    }

}
