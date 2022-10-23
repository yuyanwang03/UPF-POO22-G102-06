import java.awt.*;
import java.util.zip.CRC32;

public class Agent {
    // defining attributes
    private Vec2D position;
    private double radius;
    private Vec2D direction;
    private Vec2D target;
    private double speed;
    // do we have to define agents and worlds following the given structure or the structure we designed in the seminar

    //defining methods
    public Agent(Vec2D pos, double r){
        position = pos;
        radius = r;
    }

    public void setTarget(Vec2D initTarget){
        target = initTarget;
        direction = new Vec2D(initTarget);
        direction.subtract(position);
        direction.normalize();
    }

    public void setSpeed(double newSpeed){
        speed = newSpeed;
    }

    public void updatePosition(){
        Vec2D dir = new Vec2D(speed*direction.getX(), speed*direction.getY());
        position.add(dir);
    }
    
    public boolean reachedTarget(){
        Vec2D distance = new Vec2D(target);
        distance.subtract(position);
        if (distance.length()<radius) {return true;}
        else {return false;}
    }

    public Vec2D getPosition(){
        return position;
    }

    public double getRadius(){
        return radius;
    }

    public boolean isColliding(Agent anotherAgent){
        Vec2D distance = new Vec2D(position);
        distance.subtract(anotherAgent.getPosition());
        if (distance.length() < (radius + anotherAgent.getRadius())) {return true;}
        else {return false;}
    }

    public void paint(Graphics g) {
        int x = (int) (position.getX() - radius);
        int y = (int) (position.getY() - radius);
        int d = (int) (2*radius);
        g.setColor(Color.RED);
        g.fillOval( x, y, d, d);
    }
}
