import java.util.*;

public class Teacher {
    private String name;
    private LinkedList<Assignment> assignments;

    public Teacher(String n){
        this.name = n;
        this.assignments = new LinkedList<Assignment>();
    }

    public void addAssignment(Assignment a){
        this.assignments.add(a);
    }
    
    public String toString(){
        return this.name;
    }

    // Get all courses that this teacher have classes of
    public LinkedList<Course> getAssigmentsCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Assignment assg: this.assignments){
            // avoid duplication
            if (courses.contains(assg.getCourse())){continue;}
            courses.add(assg.getCourse());
        }
        return courses;
    }

    // Get all grouos that this teacher gives classes to
    public LinkedList<LinkedList<String> > getAssigmentsGroups(){
        LinkedList< LinkedList<String> > gps = new LinkedList<LinkedList<String> >();
        for (Assignment assg: this.assignments){
            gps.add(assg.getGroups());
        }
        return gps;
    }
}
