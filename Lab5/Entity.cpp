#ifndef _ENTITY_
#define _ENTITY_

// i was having error for the include parts tho

#include <string>
#include <cmath>

class Entity {
    private:
        Vec2D pos;
        string name;
        int energy;

    public:
        void Entity(Vec2D p, string n, int e) {
            pos = p;
            name = n;
            energy = e;
        }

        string getName() {
            return n;
        }

        void setName(string n) {
            name = n;
        }

        int compareTo(Entity e){
            //?
        }

        virtual void update() = 0;
};

#endif 

///