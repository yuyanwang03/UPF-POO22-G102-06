public class TestUniversity {
    public static void main(String[] args){
        University university = new University();
        System.out.println("\nProgram successfully read xml files\n");

        System.out.println("Students: " + university.getStudents() + "\n");
        System.out.println("Teachers: " + university.getTeachers()+ "\n");
        System.out.println("Classrooms: "+ university.getClassrooms() + "\n");
        System.out.println("Courses: " + university.getCourses() + "\n");

        // Test the queries supposing that all input is correct
        System.out.println(university.coursesOfStudent("Harry Potter"));
        System.out.println(university.teachersOfCourse("Enchantments"));
        System.out.println(university.coursesOfClassroom("13.101"));
        System.out.println(university.classroomOfTeacher("Albus Dumbledore", "0"));
        System.out.println(university.teacherOfStudent("Ginny Weasley", "Black Magic Defense", "3"));
        System.out.println(university.classroomOfStudent("Lee Jordan", "2"));
    }
}
