import java.util.*;

public class Student {
    private String name;
    private int nia;
    private LinkedList<Enrollment> enrollments;

    public Student(String name, int nia){
        this.name = name;
        this.nia = nia;
        enrollments = new LinkedList<Enrollment>();
    }

}
