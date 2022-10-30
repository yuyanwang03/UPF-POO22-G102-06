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

    public LinkedList<Teacher> getAssignmentsTeachers(){
        LinkedList<Teacher> teachers = new LinkedList<Teacher>();
        for (Assignment assg: this.assignments){
            teachers.add(assg.getTeacher());
        }
        return teachers;
    }
    
    // public LinkedList<Lecture> getLectures(){
    //     return this.lectures;
    // }

    public LinkedList<Classroom> getClassroomsWithTimeS(int t){
        LinkedList<Classroom> classrooms = new LinkedList<Classroom>();
        for (Lecture lec: this.lectures){
            if (lec.getTimeSlot()==t) { classrooms.add(lec.getClassroom()); }
        }
        return classrooms;
    }

    public LinkedList<Teacher> getTeacherWithGroup(String g){
        LinkedList<Teacher> teachrs = new LinkedList<Teacher>();
        for(Assignment assg: this.assignments){
            if (assg.getGroups().contains(g)){
                teachrs.add(assg.getTeacher());
            }
        }
        return teachrs;
    }

    public LinkedList<String> getGroupsWithType(int type){
        LinkedList<String> groups = new LinkedList<String>();
        for (Lecture lec: this.lectures){
            if (lec.getType()==type) {groups.add(lec.getGroup());}
        }
        return groups;
    }
    
}