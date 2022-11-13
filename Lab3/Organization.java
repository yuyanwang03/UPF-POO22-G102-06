import java.util.*;

public class Organization {
    private String name;
    private LinkedList<Headquarter> places;
    private LinkedList<Action> actions;
    
    public Organization(String n) {
        this.name = n;
        this.places = new LinkedList<Headquarter>();
        this.actions = new LinkedList<Action>();
    }

    public void addAction(Action a) {
        this.actions.add(a);
    }

    //
    public void addHeadquarter(Headquarter h){
        this.places.add(h);
    }

    // public Action getAction(Date d) {
        
    // }
}
