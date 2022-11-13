import java.util.*;

public class Regular extends Member{
    private Delegate responsible;
    private LinkedList<Vehicle> vehicles;

    public Regular(String n, int p, String e, Headquarter h, Delegate r){
        super(n, p, e, h);
        this.responsible = r;
        this.vehicles = new LinkedList<Vehicle>();
    }

    public void addVehicle(Vehicle v){
        this.vehicles.add(v);
    }

    public boolean participate(Action a){
        // Checks if a regular member's associated headquarter participates in a given action
        Headquarter h = this.getHeadquarter();
        Date tempDate = a.getDate();
        if (h.getAction(tempDate)!=null){return true;}
        return false;
    }
    
}
