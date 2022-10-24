import java.util.*;

public class Teacher {
    private String name;
    private LinkedList<Assignment> assignments;

    public Teacher(String n){
        this.name = n;
        this.assignments = new LinkedList<Assignment>();
    }
    
}
