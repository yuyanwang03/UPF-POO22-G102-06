# Lab 2 report

## 1. Introduction

The aim of this report is to describe how we have done lab 2, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement the design of Seminar 2, create a university management application. In this lab, we are given a class Utility and some XML files. The class Utility contains a static method readXML for parsing XML files, and a generic static method getObject. We then need to create classes corresponding to the design diagram, which are Enrollment, Assignment, Lecture, Student, Teacher, Course, Classroom and University. Finally is a class called TestUniversity that includes a main method to check if everything compiles and runs.


## 2. Implementation

The first step is to implement the classes with their attributes and methods corresponding to the design diagram. To access the LinkedList, we need to import java.util.* at the beginning of each class. Since all the relations in the diagram are association and aggregation, we make sure to include the needed attributes that correspond to the relations. We then implement the constructor methods to fill the content of those attributes. 

There are 4 classes: *Student, Teacher, Classroom, Course* that we need to include the “add” method to add the element to the corresponding link list.

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
And for each of these class we added a method called *toString()* in order to return a string value that represents these classes.

```java
    public String toString(){
        return this.attribute; // Attibute is according to what identifies each class 
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



## 3. Conclusion

After execution, it can be seen that there is not compilation error and the code works the same way as it has been defined. 

However, we need to remark that the code is written in a way that it supposes that all input given to test the methods are correct (and so, exist); we cannot ensure that it will not raise any problem if a wrong input is given. Also, a little modification we have done to this Lab is that the attibute "code" in the *Classroom* class is changed to be a String instead of being an Int (because in such way it is more easy to handle). So keep that in mind if you are testing our program with another written program.

