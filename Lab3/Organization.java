package Lab3;

import java.util.LinkedList;

public class Organization {
    private String name;
    private LinkedList<Headquarter> headquarter;
    private LinkedList<Action> action;
    
    public Organization(String n) {
        this.name = n;
    }

    public void addAction(Action a) {
        this.action.add(a);
    }

    public LinkedList<Action> getAction(LocalDateTime d) {
        
    }
}
