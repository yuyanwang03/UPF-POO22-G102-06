import java.util.*;

public class Course {
    private String name;
    private LinkedList<Enrollment> enrollments;
    private LinkedList<Assignment> assignments;
    private LinkedList<Lecture> lectures;

    public Course(String n){
        this.name = n;
        this.enrollments = new LinkedList<Enrollment>();
        this.assignments = new LinkedList<Assignment>();
        this.lectures = new LinkedList<Lecture>();
    }

    public void addEnrollment(Enrollment e){
        this.enrollments.add(e);
    }

    public void addAssignment(Assignment a){
        this.assignments.add(a);
    }

    public void addLecture(Lecture l){
        this.lectures.add(l);
    }
    
    public String toString(){
        return this.name;
    }
}