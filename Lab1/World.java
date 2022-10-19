import java.awt.*;
import java.util.Random;

import javax.swing.CellRendererPane;

public class World {
    private int width;
	private int height;
	private Agent[] agents;
	private static int capacity;
	private int margin;

    public World(int initWidth, int initHeight){
        width = initWidth;
		height = initHeight;
		margin = 30;
		capacity = 10;
		agents = new Agent[capacity];
        for (int i=0; i<capacity; i++) {
		    agents[i] = new Agent(randomPos(), randomRadius());
			agents[i].setTarget(randomPos());
			agents[i].setSpeed(randomIntXtoY(1,4));
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

    private double randomIntXtoY(int x, int y) {
        Random randomGenerator = new Random();
        return ((double) randomGenerator.nextInt(y)+x);
    }

    public void simulationStep() {
		for (int i=0; i<capacity; i++) {
			agents[i].updatePosition();
			if (agents[i].reachedTarget() == true) {
				agents[i].setTarget(randomPos());
                agents[i].setSpeed(randomIntXtoY(1,4));
			}
		}
	}

    public void paint(Graphics g){
        for (int i=0; i<capacity; i++){
            agents[i].paint(g);
        }
    }

    public void manageCollisions(){
        for (int i=0; i<capacity; i++){
            for (int j=0; j<capacity; j++){
                if (i==j) { continue; }
                if (agents[i].isColliding(agents[j])){
                    agents[i].setTarget(randomPos());
                }
            }
        }
    }

}
