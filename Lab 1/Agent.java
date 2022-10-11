public class Agent {
    // defining attributes
    private Vec2D position;
    private double radius;
    private Vec2D direction;
    private Vec2D target;
    private double speed;
    // do we have to define agents and worlds followinhg the given structure or the structure we designed in the seminar

    //defining methods
    public Agent(double posx, double posy, double r){
        position = new Vec2D(posx, posy);
        radius = r;
    }

    public void setTarget(Vec2D newTarget){
        target = newTarget;
    }

    public void setSpeed(double newSpeed){
        speed = newSpeed;
    }

    public void updatePosition(){
        position = position.add(direction);
    }
}
