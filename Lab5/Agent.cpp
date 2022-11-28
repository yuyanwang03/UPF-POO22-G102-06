#ifndef _AGENT_
#define _AGENT_

#include <string>
#include <cmath>
#include "Entity.cpp"
#include "Vec2D.hpp"

using namespace std;

class Agent: public Entity {
    private:
        double radius;
        Vec2D* dir;
        Vec2D* target;
        double speed;
    
    public:
        Agent(Vec2D* p, string n, int e, double r): Entity(p, n, e), radius(r) {};

        void setTarget(Vec2D *v) {
            target = v;
        }

        void setSpeed(double s) {
            speed = s;
        }

        void update() { 
            Vec2D direction = new Vec2D(speed * dir->getX(), speed * dir->getY());
            getPosition().add(&direction);
            delete &direction;
        }

        bool targetReached() {
            Vec2D distance = new Vec2D(target);
            distance.subtract(&getPosition());
            if (distance.length() < radius) { 
                delete &distance;
                return true;
            }
            delete &distance;
            return false;
        }

        Vec2D getPosition() {
            return Entity::getPosition();
        }

        double getRadius() {
            return radius;
        }

        bool isColliding(Agent a) {
            Vec2D distance = new Vec2D(getPosition());
            distance.subtract(&a.getPosition());
            if (distance.length() < (radius + a.getRadius())) {
                delete &distance;
                return true;
            }
            delete &distance;
            return false;
        }
};

#endif