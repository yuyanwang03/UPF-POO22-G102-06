import java.util.*;

public class Region {
    private String name;
    private LinkedList<City> cities;

    public Region(String n){
        this.name = n;
    }

    public void setCities(LinkedList<City> c){
        this.cities.addAll(c);
    }
    
}
