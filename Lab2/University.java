import java.rmi.StubNotFoundException;
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
            // Remove first 2 elements of the linked list since we know they are not groups
            assgGroups.remove(0);
            assgGroups.remove(0);
            Assignment assignment = new Assignment(assgGroups);
            Teacher teacher = Utility.getObject(assg[0], this.teachers);
            Course course = Utility.getObject(assg[1], this.courses);
            assignment.addTeacher(teacher);
            assignment.addCourse(course);
            teacher.addAssignment(assignment);
            course.addAssignment(assignment);
        }
    }

    public LinkedList<String> getStudents(){
        LinkedList<String> out = new LinkedList<String>();
        for (Student stu: this.students){
            out.add(stu.toString());
        }
        return out;
    }

    public LinkedList<String> getTeachers(){
        LinkedList<String> out = new LinkedList<String>();
        for (Teacher tea: this.teachers){
            out.add(tea.toString());
        }
        return out;
    }

    public LinkedList<String> getClassrooms(){
        LinkedList<String> out = new LinkedList<String>();
        for (Classroom clas: this.classrooms){
            out.add(clas.toString());
        }
        return out;
    }

    public LinkedList<String> getCourses(){
        LinkedList<String> out = new LinkedList<String>();
        for (Course cou: this.courses){
            out.add(cou.toString());
        }
        return out;
    }

    public LinkedList<String> coursesOfStudent(String studentName){
        Student stu = Utility.getObject(studentName, this.students);
        return Utility.toString(stu.getEnrollmentsCourses());
    }

    public LinkedList<String> teachersOfCourse(String courseName){
        Course cour = Utility.getObject(courseName, this.courses);
        return Utility.toString(cour.getAssignmentsTeachers());
    }

    public LinkedList<String> coursesOfClassroom(String classroomCode){
        Classroom clas = Utility.getObject(classroomCode, this.classrooms);
        return Utility.toString(clas.getLecturesCourses());
    }
    
    public LinkedList<String> studentsOfTeacher(String teacherName, String classroomCode){
        LinkedList<Student> studs = new LinkedList<Student>();
        Teacher tea = Utility.getObject(teacherName, this.teachers);
        Classroom clas = Utility.getObject(classroomCode, this.classrooms);

        // Cretate 2 lists, one for storing the courses the teacher gives and the other one to store the groups that the teacher is responsible of (of the course)
        LinkedList<Course> teaCourses = tea.getAssigmentsCourses();
        LinkedList<LinkedList<String> > teaGroups = new LinkedList<LinkedList<String> >();
        for (Course tc: teaCourses){
            teaGroups.add(tc.getGroupsWithClassroom(clas));
        }

        // Check for every student in the university
        for (Student stu: this.students){
            LinkedList<Course> stuCourses = stu.getEnrollmentsCourses();
            for (Course stuCourse: stuCourses){
                // Check if the student is enrolled a course that the teacher gives classes to
                if (teaCourses.contains(stuCourse)){
                    // Get student seminar group (3 digits) in this very course
                    String stuGroup = stu.getGroupInCourse(stuCourse);
                    while(stuGroup.length()>0){
                        int indxTeaCourse = teaCourses.indexOf(stuCourse);
                        // Check if the teacher gives classes to this specific group and this spefic course
                        if (teaGroups.get(indxTeaCourse).contains(stuGroup)){
                            studs.add(stu);
                            break;
                        }
                        stuGroup = stuGroup.substring(0, stuGroup.length() - 1);
                    }
                }
            }
        }

        return Utility.toString(studs);
    }

    public LinkedList<String> classroomOfTeacher(String teacherName, String time){
        LinkedList<Classroom> clasr = new LinkedList<Classroom>();
        Teacher tea = Utility.getObject(teacherName, this.teachers);
        int t = Integer.parseInt(time);

        LinkedList<Course> teaCourses = tea.getAssigmentsCourses();
        LinkedList<LinkedList<String> > teaGroups = tea.getAssigmentsGroups();

        for (int i = 0; i<teaCourses.size(); i++){
            for (int j = 0; j<teaGroups.get(i).size(); j++){
                LinkedList<Classroom> temp = (teaCourses.get(i)).getClassroomsWithTimeSAndGroup(t, (teaGroups.get(i)).get(j), false);
                if (temp!=null) {clasr.addAll(temp);}
            }
            
        }
        return Utility.toString(clasr);
    }

    public LinkedList<String> teacherOfStudent(String studentName, String courseName, String ty){
        LinkedList<Teacher> teachrs = new LinkedList<Teacher>();
        Student stu = Utility.getObject(studentName, this.students);
        Course cour = Utility.getObject(courseName, this.courses);
        int type = Integer.parseInt(ty);

        // Find the group the student has been assigned (it will always be the seminar group with three digits) to and all groups that are of type "tpye"
        String stuGroup = stu.getGroupInCourse(cour);
        LinkedList<String> groups = cour.getGroupsWithType(type);

        while(stuGroup.length()>0){
            if (groups.contains(stuGroup)){
                // Find the teacher that has been assigned to that specific group
                teachrs.addAll(cour.getTeacherWithGroup(stuGroup));
                break;
            }
            // First iteration : if "groups" does not contain the group the student belongs to, it means that the lecture is not of seminar type (3 digits),
            // So we should reduce the number of digits of the student group to get the practice group ang then the theory group
            stuGroup = stuGroup.substring(0, stuGroup.length()-1);
        }

        return Utility.toString(teachrs);
    }

    public LinkedList<String> classroomOfStudent(String studentName, String time){
        LinkedList<Classroom> cls = new LinkedList<Classroom>();
        Student stu = Utility.getObject(studentName, this.students);
        int t = Integer.parseInt(time);

        LinkedList<Course> stuCourses = stu.getEnrollmentsCourses();
        LinkedList<String> stuGroups = stu.getEnrollmentsGroup();
        for (int i=0; i<stuCourses.size(); i++){
            // Need to ensure that the lecture held in the classroom of that time slot is of the group that the student belongs
            LinkedList<Classroom> temp = stuCourses.get(i).getClassroomsWithTimeSAndGroup(t, stuGroups.get(i), true);
            if (temp!=null) {cls.addAll(temp);}
        }

        // Remove possible duplicates
        Set<Classroom> tempSet = new HashSet<Classroom>(cls);
        cls.clear();
        cls.addAll(tempSet);

        return Utility.toString(cls);
    }

    public LinkedList<String> teacherOfClassroom(String classroomCode, String time){
        LinkedList<Teacher> teas = new LinkedList<Teacher>();
        Classroom clas = Utility.getObject(classroomCode, this.classrooms);

        for (Teacher tea: this.teachers){
            if (classroomOfTeacher(tea.toString(), time).contains((clas.toString()))) {teas.add(tea);}
        }

        return Utility.toString(teas);
    }

    public LinkedList<String> studentsOfClassroom(String classroomCode, String time){
        LinkedList<Student> studs = new LinkedList<Student>();
        Classroom clas = Utility.getObject(classroomCode, this.classrooms);

        for (Student stu: this.students){
            if (classroomOfStudent(stu.toString(), time).contains((clas.toString()))) {studs.add(stu);}
        }

        return Utility.toString(studs);
    }
}
