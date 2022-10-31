import java.util.*;

public class Regular extends Member{
    private Delegate responsible;
    private LinkedList<Vehicle> vehicles;

    public Regular(String n, int p, String e, Headquarter h, Delegate r){
        super(n, p, e, h);
        this.responsible = r;
    }

    public void addVehicle(Vehicle v){
        this.vehicles.add(v);
    }

    public boolean participate(Action a){

    }
    
}
