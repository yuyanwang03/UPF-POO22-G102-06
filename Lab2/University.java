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

        LinkedList<String[]> xmlStudents = Utility.readXML("student");
        for (String[] stu: xmlStudents){ 
            this.students.add(new Student(stu[0], Integer.parseInt(stu[1])));
        }

        // System.out.println("students read\n");

        LinkedList<String[]> xmlTeachers = Utility.readXML("teacher");
        for (String[] tea:xmlTeachers){
            this.teachers.add(new Teacher(tea[0]));
        }

        // System.out.println("teachers read\n");

        LinkedList<String[]> xmlClassrooms = Utility.readXML("classroom");
        for (String[] clas:xmlClassrooms){
            this.classrooms.add(new Classroom(clas[0]));
        }

        // System.out.println("classroom read\n"); 

        LinkedList<String[]> xmlCourses = Utility.readXML("course");
        for (String[] cour:xmlCourses){
            this.courses.add(new Course(cour[0]));
        }

        // System.out.println("courses read\n");

        LinkedList<String[]> xmlLectures = Utility.readXML("lecture");

        for (String[] lec:xmlLectures){
            Lecture lecture = new Lecture(lec[4], Integer.parseInt(lec[2]), Integer.parseInt(lec[3]));
            Classroom clas = Utility.getObject(lec[0], this.classrooms);
            Course cour = Utility.getObject(lec[1], this.courses);
            lecture.addClassroom(clas);
            lecture.addCourse(cour);;
            clas.addLecture(lecture);
            cour.addLecture(lecture);
        }

        // System.out.println("lectures read\n");

        LinkedList<String[]> xmlEnrollments = Utility.readXML("enrollment");
        for (String[] enr:xmlEnrollments){
            Enrollment enrollment = new Enrollment(enr[2]);
            Student student = Utility.getObject(enr[0], this.students);
            Course course = Utility.getObject(enr[1], this.courses);
            enrollment.addStudent(student);
            enrollment.addCourse(course);
            student.addEnrollment(enrollment);
            course.addEnrollment(enrollment);
        }

        // System.out.println("enrolls read\n");

        LinkedList<String[]> xmlAssignment = Utility.readXML("assignment");
        for (String[] assg:xmlAssignment){
            LinkedList<String> assgGroups = new LinkedList<String>(Arrays.asList(assg));
            assgGroups.remove(0);
            assgGroups.remove(1);
            Assignment assignment = new Assignment(assgGroups);
            Teacher teacher = Utility.getObject(assg[0], this.teachers);
            Course course = Utility.getObject(assg[1], this.courses);
            assignment.addTeacher(teacher);
            assignment.addCourse(course);
            teacher.addAssignment(assignment);
            course.addAssignment(assignment);
        }

    }
}
