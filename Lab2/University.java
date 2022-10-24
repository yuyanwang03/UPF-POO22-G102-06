import java.util.*;

public class University {
    private LinkedList<Student> students;
    private LinkedList<Teacher> teachers;
    private LinkedList<Classroom> classrooms;
    private LinkedList<Course> courses;

    public University(){
        this.students = new LinkedList<Student>();
        this.teachers = new LinkedList<Teacher>();
        this.classrooms = new LinkedList<Classroom>();
        this.courses = new LinkedList<Course>();
    }
}
