#ifndef _AGENT_
#define _AGENT_

#include <iostream>
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
        Agent(Vec2D* p, string n, int e, double r): Entity(p, n, e), radius(r), speed(1) {};

        void setTarget(Vec2D *v) {
            target = v;
            Vec2D temp(v);
            temp.subtract(getPosition());
            temp.normalize();
            // Assign the direction according to the target
            dir = new Vec2D(temp);
        }

        void setSpeed(double s) {
            speed = s;
        }

        void update() {
            // Check if the agent has reached to its target
            if (targetReached()) {
                // Print out a message informing that the agent has reached the target
                cout << Entity::getName() << " has reached its target "; target->print();
                // Exit the method without doing any change
                return;
            }
            Vec2D direction(speed * dir->getX(), speed * dir->getY());
            (*getPosition()).add(&direction);
        }

        void print() {
            cout << Entity::getName() << " with energy " << Entity::getEnergy() << " and speed " << speed << " is at ";
            (*getPosition()).print();
        }

        bool targetReached() {
            Vec2D distance(target);
            Vec2D position(getPosition());
            distance.subtract(&position);
            if (distance.length() < radius) { 
                return true;
            }
            return false;
        }

        Vec2D* getPosition() {
            return Entity::getPosition();
        }

        double getRadius() {
            return radius;
        }

        bool isColliding(Agent a) {
            Vec2D distance(getPosition());
            Vec2D position(a.getPosition());
            distance.subtract(&position);
            return distance.length() < (radius + a.getRadius()) ? true : false;
        }
};

#endif