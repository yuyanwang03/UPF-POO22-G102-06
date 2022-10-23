
<!--
>1. An introduction where the problem is described. For example, what
should the program do? Which classes do you have to define? Which
methods do you have to implement for these classes?
>2. A description of possible alternative solutions that were discussed, and a
description of the chosen solution and the reason for choosing this solution
rather than others. It is also a good idea to mention the related theoretical
concepts of object-oriented programming that were applied as part of the
solution.
>3. A conclusion that describes how well the solution worked in practice,
i.e. did the tests show that the classes were correctly implemented? You
can also mention any difficulties during the implementation as well as any
doubts you might have had.
--->


# Lab 1 report

## 1. Introduction

The aim of this report is to describe how we have done lab 1, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement the design of Seminar 1, create a simulation environment containing moving agents. Beside the given classes *TestWorld*, *Vec2D* and *WorldGUI*, we need to implement 2 more classes, which are *World* and *Agent*.


## 2. Implementation

### ***Agent Class***

First, let’s start with Agent class. We follow the attributes stated in the diagram design, however, instead of defining some attributes separately, we substituted by 2-dimensional vectors, which we can do by calling the class Vec2D, such as position, direction and target. 

```java
    private Vec2D position;
    private double radius;
    private Vec2D direction;
    private Vec2D target;
    private double speed;
```

Then, we continue with implementing methods. The constructor method is used to give the initialization for position and radius of an agent. Beside the getter and setter methods which are used to set value for an attribute or return the value of an attribute, we need to add some more methods. 

The method setTarget performs two operations: set the position of the target and set the current position of the agent. We can do this by first initialize a new target and get its direction by calling methods subtract and normalize from class Vec2D. 

```java
public void setTarget(Vec2D initTarget){
        target = initTarget;
        direction = new Vec2D(initTarget);
        direction.subtract(position);
        direction.normalize();
    }
```

For method updatePosition, we use the method add of class Vec2D to sum the position of with its speed multiplied by the direction (pos + speed * dir). 

```java
public void updatePosition(){
        Vec2D dir = new Vec2D(speed*direction.getX(), speed*direction.getY());
        position.add(dir);
    }
```

To check if an agent has reached its target, we create a method targetReached which returns a boolean. Similar to the method updatePosition, however this time we do not need to normalize the vector, but to compare if the length of the vector is less than the agent’s radius, if it is, the function returns true. 

```java
public boolean reachedTarget(){
        Vec2D distance = new Vec2D(target);
        distance.subtract(position);
        if (distance.length()<radius) {return true;}
        else {return false;}
    }
```

We also have a method very similar to check if an agent has reached its target, but this time is to check if it is colliding with another agent. To do so, we also have a boolean that returns true if the distance between two agents is less than the sum of the two agents’ radius. 

```java
public boolean isColliding(Agent anotherAgent){
        Vec2D distance = new Vec2D(position);
        distance.subtract(anotherAgent.getPosition());
        if (distance.length() < (radius + anotherAgent.getRadius())) {return true;}
        else {return false;}
    }
```

### ***World Class***

Now, let’s continue with World class. First thing we do is also to create attributes from the diagram and add one more which is magrin with value 30 to ensure that the agents do not exit the environment. Another thing to highlight is that the attribute capacity has been set to be static because as the exercice stated, the value of this attribute will not change during the implemetation of the code.

```java
    private int width;
	private int height;
	private Agent[] agents;
	private static int capacity;
	private int margin;
```

For the constructor method, we set the initial values for width and height, then create 10 agents (value of attribute capacity) with random targets, random radius and random speeds by calling methods from Agent class. We want each of the agents to have a random position and radius. Now, in order for our agents to move, we need to implement the method simulationStep. In this method, we first update each agents’ positions, then to check if they have reached their current targets by calling method reachedTarget from class Agent. If they have, we need to set a new random target and new random speed. If they have not, we just need to update their positions again.

```java
public void simulationStep() {
    for (int i=0; i<capacity; i++) {
        agents[i].updatePosition();
        if (agents[i].reachedTarget() == true) {
            agents[i].setTarget(randomPos());
            agents[i].setSpeed(randomIntXtoY(1,4));
        }
    }
}
```

In addition to all that, there is another method to control the possible collisions between the agents of the world. The method iterates through all pairs of agents to check if they are colliding; if so, then it will change the target of the agent to a random new one.

```java
public void manageCollisions(){
    for (int i=0; i<capacity; i++){
        for (int j=0; j<capacity; j++){
            if (i==j) { continue; }
            if (agents[i].isColliding(agents[j])){
                agents[i].setTarget(randomPos());
            }
        }
    }
}
```

## 3. Conclusion

After execution, it can be seen that there is not compilation error and the code works the same way as it has been defined. 

However, the collitions between agents might not be handled in the best way: since the code is setting a random target position when two agents collide, there is a high chance that the new target position is similar to the old one so the agents will still be colliding; this can be observed because of the struggling between the agents that are in the same spot.

The solution we have tried for this problem was to use Physics formula to make these to be 2D elastic collisions. The formula is defined as follows:

![Image](elastic%20collision%20formula.png "2D elastic collision")

But because of the way class *Vec2D* has been defined, it was hard to implememt vector operations to get the final correct result. Apart from that, in order to make the whole thing make sense, we would need to also define methods to control situations when agents arrive to the margins and make them bounce off. All these previously mentioned work were too additional so we gave up applying complicated changes to the code.

