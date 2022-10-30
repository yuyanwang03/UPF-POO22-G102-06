import java.util.*;

public class Assignment {
    private LinkedList<String> groups;
    private Teacher teacher;
    private Course course;

    public Assignment(LinkedList<String> g){
        this.groups = g;
    }

    public void addTeacher(Teacher t){
        this.teacher = t;
    }

    public void addCourse(Course c){
        this.course = c;
    }
    
    public Teacher getTeacher(){
        return this.teacher;
    }

    public Course getCourse(){
        return this.course;
    }
}
