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
    
    public void addEnrollment(Enrollment e){
        enrollments.add(e);
        // e.addStudent(this);
    }

    public String toString(){
        return this.name;
    }

    // Get all courses this student has been enrolled in
    public LinkedList<Course> getEnrollmentsCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Enrollment enroll: this.enrollments){
            courses.add(enroll.getCourse());
        }
        return courses;
    }

    // Get all groups this student has been enrolled in
    public LinkedList<String> getEnrollmentsGroup(){
        LinkedList<String> groups = new LinkedList<String>();
        for (Enrollment enroll: this.enrollments){
            groups.add(enroll.getSeminarGroup());
        }
        return groups;
    }

    // Get the group of a given course that the student belongs to
    public String getGroupInCourse(Course cor){
        for (Enrollment enr: this.enrollments){
            if (enr.getCourse()==cor){
                return enr.getSeminarGroup();
            }
        }
        // if not found, student is not enrolled in the corresponding course
        System.out.println("Student " + this.name + " is not enrolled in the course called " + cor.toString());
        return "";
    }

}
