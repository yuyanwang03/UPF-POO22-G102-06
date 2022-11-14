# Lab 3 report

## 1. Introduction

The aim of this report is to describe how we have done lab 3, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement an application using libraries and inheritance. By using the design of Seminar 3, we are going to implement an organization management application. Similar to the previous lab, this time we are given a directory src with definition of several classes and some XML files. We are also given the class Utility which contains a generic static method getObject, QRLib and Image classes with two static methods generateQRCodeImage and decodeQRCodeImage for creating and decoding QR Images. For our part, we need to create 11 classes corresponding to the design diagram, which are *Organization, Action, Availability, Headquarter, InfoAction, City, Region, Member, Regular, Delegate and Vehicle*. Finally, we create a *TestDelegate* class which includes the main method.

The UML of the program is defined as follows:

![Image](Lab3UML.png "Lab3UML")


## 2. Implementation

In the explanation of the implementation of this Lab3, we will not go through all the existing classes/methods because some of them are just to simple and are not worth-mentioning. We will just mention some of the essential aspects of the code.

First of all, we follow the instructions of the teacher during the lab session to configure the classpath. Then, as usual, we include all the attributes and methods corresponding to the design diagram. There are no difficulties during this step. However, we need to take into account when to use linked lists and when to pass the element itself. When the cardinality is 1, for example, each headquarter can only belong to 1 organization, then we use the element organization itself. On the other hand, the organization can have many headquarters, then we need to pass a list of headquarters since the cardinality is higher than 1.

### ***Reading the XML files***

It is implemented in a similar way to the previous Lab. We have created LinkedLists and isinstances to store the information retrieved from the xml files. Variables *cities, headquarters and delegates* are not crutial since all information can be stored in merely variables *regions and organization*; however, the existece of these can simplify a lot the process of code testing, so that is why we have incorporated these.

```java
LinkedList<Region> regions = new LinkedList<Region>();
LinkedList<City> cities = new LinkedList<City>();
Organization organization = new Organization("Lab3 Organization");
LinkedList<Headquarter> headquarters = new LinkedList<Headquarter>();
LinkedList<Delegate> delegates = new LinkedList<Delegate>();
```

Following is an example of how the program reads the file *regions.xml*

```java
LinkedList<String[]> xmlRegions = Utility.readXML("region");
for (String[] reg: xmlRegions){ 
    LinkedList<City> regionCities = new LinkedList<City>();
    Region tempReg = new Region(reg[0]);
    String[] tempCities = Arrays.copyOfRange(reg, 1, reg.length/2+1);
    String[] tempPopulation = Arrays.copyOfRange(reg, reg.length/2+1, reg.length);
    for (int i =0; i<tempCities.length; i++){
        City tempCity = new City(tempCities[i], Integer.parseInt(tempPopulation[i]));
        cities.add(tempCity);
        regionCities.add(tempCity);
    }

    tempReg.setCities(regionCities);
    regions.add(tempReg);
}
```

### ***Organization Class***

The method getAction with return an Action with a given date. If there does not exist any action that will be held in the given date, the method will return a null pointer.
```java
public Action getAction(Date d) {
    for (int i = 0; i<this.actions.size(); i++){
        if (actions.get(i).getDate().equals(d)){
            return actions.get(i);
        }
    }
    return null;
}
```

The method getDelegates() returns a list of the head-delegates of each headquarter belonging to the organization. The program will go through all the headquarters since each headquarter has a delegate who is the head of the headquarter. 

```java
public LinkedList<Delegate> getDelegates(){
    LinkedList<Delegate> out = new LinkedList<Delegate>();
    for (Headquarter h : this.places){
        out.add(h.getHead());
    }
    // Print it with System.out.println(Utility.toString(out));
    return out;
}
```

Similarly, the method getAllMembers() which returns the list of members also goes through all the headquarters of the organization and access the members of each headquarter then save them into the linked list.

```java
public LinkedList<Member> getAllMembers(){
    LinkedList<Member> out = new LinkedList<Member>();
    for (Headquarter h : this.places){
        out.addAll(h.getMembers());
    }
    // Print it with System.out.println(Utility.toString(out));
    return out;
}
```

These are support methods to be used in the code testing part, along with the methods getActions() and getAllInfoActions()

```java
public LinkedList<Action> getActions(){
    return this.actions;
}

public LinkedList<InfoAction> getAllInfoActions(){
    LinkedList<InfoAction> out = new LinkedList<InfoAction>();
    for (Action a : this.actions){
        out.addAll(a.getDevelopedAction());
    }
    // Print it with System.out.println(Utility.toString(out));
    return out;
}
```










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



## 3. Conclusion

After the execution, there are no compilation errors. We were able to implement the structure of the organization and the territory, including the functionalities that allow to sign up new members and actions.

Regarding the given source code (*Utility.java*), we have changed the file directory, if it does not work on your computer, please change the path according to your precise situation. Following is the line of code we have modified.

```java
File input = new File("Lab3/src/" + type + "s.xml"); // Modified manually
```

















We have implemented all queries defined in the lab. After execution, it can be seen that there is not compilation error and the code works the same way as it has been defined. Nevertheless, we must include here that maybe the way we have implemented each query is not the most effective method; but at least, we are achieving our main purpose.

Apart from that, we need to remark that the code is written in a way that it supposes that all input given to test the methods are correct (and so, exist); we cannot ensure that it will not raise any problem if a wrong input is given. Also, a little modification we have done to this Lab is that the attibute "code" in the *Classroom* class is changed to be a String instead of being an Int (because in such way it is more easy to handle). So keep that in mind if you are testing our program with another written program. 



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

