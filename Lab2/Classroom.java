import java.util.*;

public class Classroom {
    public String code;
    private LinkedList<Lecture> lectures;

    public Classroom(String c){
        this.code = c;
        this.lectures = new LinkedList<Lecture>();
    }

    public void addLecture(Lecture l){
        this.lectures.add(l);
    }
    
    public String toString(){
        return this.code;
    }

    // Get all courses that are held in the classroom
    public LinkedList<Course> getLecturesCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Lecture lec: this.lectures){
            // avoid duplication
            if (courses.contains(lec.getCourse())){continue;}
            courses.add(lec.getCourse());
        }
        return courses;
    }
}
