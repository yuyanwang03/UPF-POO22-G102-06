# Lab 2 report

## 1. Introduction

The aim of this report is to describe how we have done lab 2, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement the design of Seminar 2, create a university management application. In this lab, we are given a class Utility and some XML files. The class Utility contains a static method readXML for parsing XML files, and a generic static method getObject. We then need to create classes corresponding to the design diagram, which are Enrollment, Assignment, Lecture, Student, Teacher, Course, Classroom and University. Finally is a class called TestUniversity that includes a main method to check if everything compiles and runs.


## 2. Implementation

In the explanation of the implementation of this Lab2, we will not go through all the existing classes because some of them are just to simple and are not worth-mentioning. We will just mention some of the essential aspects of the code.

The classes defined in this lab are mainly: *Assignment, Classroom, Course, Enrollment, Lecture, Student, Teacher, University and Utility*.

The first step is to implement the classes with their attributes and methods corresponding to the design diagram. To access the LinkedList, we need to import java.util.* at the beginning of each class. Since all the relations in the diagram are association and aggregation, we make sure to include the needed attributes that correspond to the relations. We then implement the constructor methods to fill the content of those attributes. 

Classes *Student, Teacher, Classroom, Course* are the ones that we need to include the “add” method to add the element to the corresponding link list.

```java
    public void addEnrollment(Enrollment e){
        this.enrollments.add(e);
    }

    public void addAssignment(Assignment a){
        this.assignments.add(a);
    }

    public void addLecture(Lecture l){
        this.lectures.add(l);
    }
```
And for each of these classes we added a method called *toString()* in order to return a string value that represents these classes.

```java
    public String toString(){
        return this.attribute; // Attibute is according to what identifies each class 
    }
```

### ***Classroom Class***

The method *getLectureCourses()* is used to get a list of courses that will be teached in a Classroom. This funcion's objective is accomplished by going through all the lectures that are being held in the classroom and accessing the course of each lecture and saving it into the LinkedList if it has not been included yet.

```java
    public LinkedList<Course> getLecturesCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Lecture lec: this.lectures){
            // avoid duplication
            if (courses.contains(lec.getCourse())){continue;}
            courses.add(lec.getCourse());
        }
        return courses;
    }
```

### ***Course Class***

There are several additional methods defined in this class:

* *getAssignmentsTeachers()* will return a list of teachers that will give classes for the course.

    ```java
    public LinkedList<Teacher> getAssignmentsTeachers(){
        LinkedList<Teacher> teachers = new LinkedList<Teacher>();
        for (Assignment assg: this.assignments){
            // avoid duplication
            if (teachers.contains(assg.getTeacher())){continue;}
            teachers.add(assg.getTeacher());
        }
        return teachers;
    }
    ```

* *getGroupsWithClassroom(Classroom clas)* will return a list of groups (String) that belong to the course and have classes in a given classroom.

    ```java
    public LinkedList<String> getGroupsWithClassroom(Classroom clas){
        LinkedList<String> groups = new LinkedList<String>();
        for(Lecture lec: this.lectures){
            if(lec.getClassroom().equals(clas)){
                groups.add(lec.getGroup());
            }
        }
        return groups;
    }
    ```

* *getClassroomsWithTimeS(int t)* will return a list of classrooms where the course will have lectures in a given timeslot.

    ```java
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
    ```

* *getClassroomsWithTimeSAndGroup(int t, String g, boolean rangeSem)* will return a list of classrooms where this course will have lectures in a given timeslot and to a given group. The boolan parameter of this function is to specify the range of search. If it is set to be false, it will mean that the group that we are looking for is merely the given sequnce of group digits; if it is set to be true, the method will look not only for the given sequence of group but also for its superclass groups.
For instance, if the group parameter is "121" (seminar group), the program will also look if group "12" (Lab group) and "1" (Theory group) satisfy the given condition.

    ```java
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
    ```

* *getTeacherWithGroup(String g)* will return a list of teachers that teach this course and to a specific group.

    ```java
    public LinkedList<Teacher> getTeacherWithGroup(String g){
        LinkedList<Teacher> teachrs = new LinkedList<Teacher>();
        for(Assignment assg: this.assignments){
            if (assg.getGroups().contains(g)){
                teachrs.add(assg.getTeacher());
            }
        }
        return teachrs;
    }
    ```

* *getGroupsWithType(int type)* will return a list of groups that belong to this course and that have classes of type "type".

    ```java
    public LinkedList<String> getGroupsWithType(int type){
        LinkedList<String> groups = new LinkedList<String>();
        for (Lecture lec: this.lectures){
            if (lec.getType()==type) {groups.add(lec.getGroup());}
        }
        return groups;
    }
    ```

### ***Student Class***

* *getEnrollmentsCourses()* will return a list of courses that the student has been enrolled in.

    ```java
    // Get all courses this student has been enrolled in
    public LinkedList<Course> getEnrollmentsCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Enrollment enroll: this.enrollments){
            courses.add(enroll.getCourse());
        }
        return courses;
    }
    ```

* *getEnrollmentsGroup()* will return a list of all seminar groups that the student belongs.

    ```java
    public LinkedList<String> getEnrollmentsGroup(){
        LinkedList<String> groups = new LinkedList<String>();
        for (Enrollment enroll: this.enrollments){
            groups.add(enroll.getSeminarGroup());
        }
        return groups;
    }
    ```

* *getGroupInCourse(Course cor)* will return the seminar group that the student belongs to of a given course. If the student is not enrolled in the given course, the method will warn the user about it in the console
    
    ```java
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
    ```

### ***Teacher class***

* *getAssigmentsCourses()* will retur a list of courses that the teacher will give class about.
    
    ```java
    public LinkedList<Course> getAssigmentsCourses(){
        LinkedList<Course> courses = new LinkedList<Course>();
        for (Assignment assg: this.assignments){
            // avoid duplication
            if (courses.contains(assg.getCourse())){continue;}
            courses.add(assg.getCourse());
        }
        return courses;
    }
    ```

* *getAssigmentsGroups()* will return a list of lists of groups that the teacher will give classes to. The order of these lists corresponds to the order of the list of courses obtained with the previous method.
    
    ```java
    public LinkedList<LinkedList<String> > getAssigmentsGroups(){
        LinkedList< LinkedList<String> > gps = new LinkedList<LinkedList<String> >();
        for (Assignment assg: this.assignments){
            gps.add(assg.getGroups());
        }
        return gps;
    }
    ```

### ***University Class***

The attributes of this class are the linked lists from classes Student, Teacher, Classroom and Course:

```java
    private LinkedList<Student> students;
    private LinkedList<Teacher> teachers;
    private LinkedList<Classroom> classrooms;
    private LinkedList<Course> courses;
```

For the constructor of this class, we need to parse the XML files using the method readXML from the class Utility. For the 4 classes Student, Teacher, Classroom and Course, we only need to create an instance of the entity and add it to the corresponding attribute list. An example of *Student* is as follows: (other classes does not differ a lot from this example)

```java
    LinkedList<String[]> xmlStudents = Utility.readXML("student");
        for (String[] stu: xmlStudents){ 
            this.students.add(new Student(stu[0], Integer.parseInt(stu[1])));
        }
```

For *Enrollment, Assignment and Lecture*, since they are associative classes the instantiation of them will be different; method *getObject()* defined in *Utility* class would be used. The trickiest one of them is the instantiation of *Assignment*, because it will take a LinkedList of Strings as an argument. The way we implemented is to create a copy of the input as a list and remove the first 2 elements since they are not part of the information given about the groups. 

```java
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
```

The getter methods' return values of this class are LinkedList of String as it is demanded. Again, they are quite similar when it comes about the structure so following will be just an example.

```java
    public LinkedList<String> getTeachers(){
        LinkedList<String> out = new LinkedList<String>();
        for (Teacher tea: this.teachers){
            out.add(tea.toString());
        }
        return out;
    }
```

All following methods are used to implement the queries defined for this lab; they will indeed use methods that are defined in previous classes. It is straight-forward to see what each method does.

```java
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
```


## 3. Conclusion

We have implemented all queries defined in the lab. After execution, it can be seen that there is not compilation error and the code works the same way as it has been defined. Nevertheless, we must include here that maybe the way we have implemented each query is not the most effective method; but at least, we are achieving our main purpose.

Apart from that, we need to remark that the code is written in a way that it supposes that all input given to test the methods are correct (and so, exist); we cannot ensure that it will not raise any problem if a wrong input is given. Also, a little modification we have done to this Lab is that the attibute "code" in the *Classroom* class is changed to be a String instead of being an Int (because in such way it is more easy to handle). So keep that in mind if you are testing our program with another written program. 

Regarding the given source code (*Utility.java*), we have changed the file directory, if it does not work on your computer, please change the path according to your precise situation. Following is the line of code we have modified.

```java
File input = new File("Lab2/src/" + type + "s.xml"); // Modified manually
```

Another thing to say about the given source code is that there were brackets missing inside the method *getObject()* so we added them in order to make it work.

```java
    public static <T> T getObject( String desc, LinkedList< T > objectList ) {
        for ( T object : objectList ){
            if ( desc.equals( object.toString() ) )
                return object;
		}
        return null;
    }
```

