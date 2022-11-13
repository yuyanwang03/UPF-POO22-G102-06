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
        // Print it with System.out.println(Utility.toString(out));
        return out;
    }

    public LinkedList<Member> getAllMembers(){
        LinkedList<Member> out = new LinkedList<Member>();
        for (Headquarter h : this.places){
            out.addAll(h.getMembers());
        }
        // Print it with System.out.println(Utility.toString(out));
        return out;
    }

    public LinkedList<Action> getActions(){
        return this.actions;
    }

    public LinkedList<InfoAction> getAllInfoActions(){
        LinkedList<InfoAction> out = new LinkedList<InfoAction>();
        for (Action a : this.actions){
            out.addAll(a.getDevelopedAction());
        }
        // Print it with System.out.println(Utility.toString(out));
        return out;
    }
    
    public LinkedList<Headquarter> getPlaces(){
        return this.places;
    }
}
