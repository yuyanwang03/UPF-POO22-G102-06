
import java.awt.*;
import java.lang.annotation.Target;

import javax.swing.*;

public class WorldGUI extends JPanel {

	// define a world attribute here
	private int width;
	private int height;
	private Agent[] agents;
	private static int capacity;
	private int margin;


	public WorldGUI(int initWidth, int initHeight, int capacity) {
		// initialize the world here
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
		

		// Place the GUI inside a window and show it on the screen
		JFrame frame = new JFrame("World");
		setPreferredSize(new Dimension(800, 600));
		frame.add(this);
		frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Perform 1000 simulation steps
		try { simulate(1000); }
		catch (Exception e) {}
	}

	private Vec2D randomPos() {
		double x = margin + Math.random()*(width - 2 * margin);
		double y = margin + Math.random()*(height - 2 * margin);
		return new Vec2D( x , y );
	}

	private double randomRadius() {
		return 5 + Math.random() * (margin - 5) ;
	}
		
	
	public void simulate(int steps) throws Exception {
		for (int i = 0; i < steps; ++i) {
			// sleep for 10 milliseconds
			Thread.sleep(10);
			
			// perform a simulation step of the world
			simulationStep();
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		// paint the world
		for (int i=0; i<capacity; i++) {
			agents[i].paint(g);
		}
	}

	public void simulationStep() {
		for (int i=0; i<capacity; i++) {
			agents[i].updatePosition();
			if (agents[i].reachedTarget() == true) {
				agents[i].setTarget(randomPos());
			}
		}
	}
}
