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

    // Get all teachers of this course
    public LinkedList<Teacher> getAssignmentsTeachers(){
        LinkedList<Teacher> teachers = new LinkedList<Teacher>();
        for (Assignment assg: this.assignments){
            // avoid duplication
            if (teachers.contains(assg.getTeacher())){continue;}
            teachers.add(assg.getTeacher());
        }
        return teachers;
    }
    
    // Get groups that belong to this course that have classes in a given the classroom
    public LinkedList<String> getGroupsWithClassroom(Classroom clas){
        LinkedList<String> groups = new LinkedList<String>();
        for(Lecture lec: this.lectures){
            if(lec.getClassroom().equals(clas)){
                groups.add(lec.getGroup());
            }
        }
        return groups;
    }

    // Get classrooms where this course will have lectures in a given timeslot
    public LinkedList<Classroom> getClassroomsWithTimeS(int t){
        LinkedList<Classroom> classrooms = new LinkedList<Classroom>();
        for (Lecture lec: this.lectures){
            if (lec.getTimeSlot()==t){ 
                // avoid duplication
                if (classrooms.contains(lec.getClassroom())){continue;}
                classrooms.add(lec.getClassroom());
            }
        }
        return classrooms;
    }

    public LinkedList<Classroom> getClassroomsWithTimeSAndGroup(int t, String g, boolean rangeSem){
        LinkedList<Classroom> classrooms = new LinkedList<Classroom>();
        for (Lecture lec: this.lectures){
            boolean sameGroup = false;
            if (lec.getTimeSlot()!=t) {continue;}
            if (rangeSem){
                while (g.length()>0){
                    if (g.equals(lec.getGroup())) {sameGroup = true;}
                    g = g.substring(0, g.length() - 1);
                }
            } else{
                if (g.equals(lec.getGroup())) {sameGroup = true;}
            }
            if (sameGroup==false) {continue;}
            if (classrooms.contains(lec.getClassroom())) {continue;}
            classrooms.add(lec.getClassroom());
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