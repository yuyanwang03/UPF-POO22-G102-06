
#include <iostream>
#include "Vec2D.hpp"
#include "Agent.cpp"
using namespace std;

int main() {
	// create instances directly
	cout << "Lab5 Code Testing\n\n";
	cout << "------------------------- Vec2D Test -------------------------" << endl;
	Vec2D v1(2, 3);
	Vec2D v2(4, 5);
	cout << "vec1: "; v1.print();
	cout << "vec2: "; v2.print();

	// pass v1 as a pointer
	v2.subtract(&v1);
	cout << "vec2 subtract vec1: "; v2.print();
	cout << "vec2 length: " << v2.length() << endl;
	
	// create instances using pointers
	Vec2D * v3 = new Vec2D(-1, 1);
	Vec2D * v4 = new Vec2D(2, 5);
	cout << "vec3: "; v3->print();
	cout << "vec4: "; v4->print();
	
	// use "->" to call methods on a pointer
	// v3 is already a pointer
	v4->subtract(v3);
	cout << "vec4 subtract vec3: "; v4->print();
	cout << "vec4 length: " << v4->length() << endl;

	cout << "\n------------------------- Agent Test -------------------------" << endl;
	// Create an instance of an agent
	Agent a1(v4, "Agent 1", 40, 3);
	// Set target
	a1.setTarget(new Vec2D(10, 10));
	// Set speed
	a1.setSpeed(3);
	// Print its info
	a1.print();
	// Update position
	a1.update();
	a1.update();
	a1.print();
	a1.update();
	a1.update();
	Agent a2(v3, "Agent 2", 50, 4);
	a2.setTarget(new Vec2D(10, 10));
	a2.setSpeed(6);
	a2.print();
	a1.compareTo(&a2);
	cout << boolalpha << "Colliding? " << a2.isColliding(a1) << endl;
	a2.update();
	a2.update();
	a2.update();
	cout << "Colliding? " << a2.isColliding(a1) << endl;

	// instances created using "new" have to be deleted
	delete v3;
	delete v4;

}