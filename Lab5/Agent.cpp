#ifndef _AGENT_
#define _AGENT_

#include <string>
#include <cmath>

class Agent: public Entity {
    private:
        double radius;
        Vec2D dir;
        Vec2D target;
        double speed;
    
    public:
        void Agent(Vec2D p, string n, int e, double r) :  Entity(p, n, e) {
            radius = r;
        }

        void setTarget(Vec2D v) {
            target = v;
        }

        void setSpeed(double s) {
            speed = s;
        }

        void update() { 
            //not sure what they mean by update, im doing if they want to update postion
            Vec2D *direction = new Vec2D(speed * dir->getX(), speed * dir->getY());
            pos.add(direction);
        }

        bool targetReached() {
            Vec2D distance = new Vec2D(target);
            distance.subtract(pos);
            if (distance->length() < radius) {
                return true;
            }
            else {
                return false;
            }
        }

        Vec2D getPosition() { //not in the diagram but i think we need it for the next method
            return pos;
        }

        Vec2D getRadius() {
            return radius;
        }

        bool isColliding(Agent a) {
            Vec2D distance = new Vec2D(pos);
            distance.subtract(a.getPosition());
            if (distance.length() < (radius + a.getRadius())) {
                return true;
            }
            else {
                return false;
            }
        }
}   

#endif