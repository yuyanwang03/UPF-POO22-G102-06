import java.util.*;

public class City {
    private String name; 
    private int population;
    private LinkedList<Headquarter> hqs;
    
    public City(String n, int p){
        this.name = n;
        this.population = p;
        this.hqs = new LinkedList<Headquarter>();
    }

    public void addHQ(Headquarter h){
        this.hqs.add(h);
    }
    
    public String toString(){
        return this.name;
    }

}
