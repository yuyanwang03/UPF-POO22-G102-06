
import java.awt.*;
import javax.swing.*;

public class WorldGUI extends JPanel {

	int max_num_agents = 30;
	int margin = 10;

	// define a world attribute here
	private int width;
	private int height;
	private Agent[max_num_agents] agents;
	private int numAgents;
	
	public WorldGUI(int initWidth, int initHeight, int initNumAgents) {
		// initialize the world here
		width = initWidth;
		height = initHeight;
		numAgents = initNumAgents;
		for (int i=0; i<margin; i++) {
			Agent agents[i] = new agent;
			
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
	
	public void simulate(int steps) throws Exception {
		for (int i = 0; i < steps; ++i) {
			// sleep for 10 milliseconds
			Thread.sleep(10);
			
			// perform a simulation step of the world
			
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		// paint the world
	}

	public void addAgent(Agent agent) {
		agents.push(agent);
	}

}
