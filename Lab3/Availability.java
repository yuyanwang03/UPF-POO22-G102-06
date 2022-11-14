import java.util.*;

public class Availability {
    private LinkedList<String> days;
    private LinkedList<Integer> hours;

    public Availability(LinkedList<String> d, LinkedList<Integer> h){
        this.days = new LinkedList<String>(d);
        this.hours = new LinkedList<Integer>(h);
    }
}
