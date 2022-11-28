#ifndef _ENTITY_
#define _ENTITY_

#include <string>
#include <cmath>
#include "Comparable.hpp"
#include "Nameable.hpp"
#include "Vec2D.hpp"
#include "Entity.cpp"

using namespace std;

class Entity: public Nameable, Comparable<Entity>{
    private:
        Vec2D* pos;
        string name;
        int energy;

    public:
        Entity(Vec2D* p, string n, int e): pos(p), name(n), energy(e) {}

        string getName() {return name;}

        void setName(string n) {name = n;}

        int compareTo(Entity* e){
            if (energy == e->getEnergy()){ return 0;}
            else if (energy > e->getEnergy()) {return 1;}
            return -1;
        }

        int getEnergy() {return energy;}

        virtual void update() = 0;

        Vec2D getPosition(){ return *pos;}

};

#endif 
