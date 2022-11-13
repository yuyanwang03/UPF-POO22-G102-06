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

    
    public void addHeadquarter(Headquarter h){
        this.places.add(h);
    }

    public Action getAction(Date d) {
        for (int i = 0; i<this.actions.size(); i++){
            if (actions.get(i).getDate().equals(d)){
                return actions.get(i);
            }
        }
        return null;
    }

    public LinkedList<Delegate> getDelegates(){
        LinkedList<Delegate> out = new LinkedList<Delegate>();
        for (Headquarter h : this.places){
            out.add(h.getHead());
        }
        return out;
    }
}
