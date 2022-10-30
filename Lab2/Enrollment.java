import java.util.*;

public class Enrollment {
    private String seminarGroup;
    private Student student;
    private Course course;

    public Enrollment(String sg){
        this.seminarGroup = sg;
    }

    public void addStudent(Student s){
        this.student = s;
    }

    public void addCourse(Course c){
        this.course = c;
    }

    public Course getCourse(){
        return this.course;
    }
    
    public String getSeminarGroup(){
        return this.seminarGroup;
    }
}
