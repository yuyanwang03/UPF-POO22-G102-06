import java.util.*;

public class Region {
    private String name;
    private LinkedList<City> cities;

    public Region(String n){
        this.name = n;
        this.cities = new LinkedList<City>();
    }

    public void setCities(LinkedList<City> c){
        this.cities.addAll(c);
    }
    
    //
    public LinkedList<City> getCities(){
        return this.cities;
    }
}
