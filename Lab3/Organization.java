import java.util.*;

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

    public LinkedList<Action> getAction(Date d) {
        
    }
}
