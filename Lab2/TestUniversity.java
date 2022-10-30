public class TestUniversity {
    public static void main(String[] args){
        University university = new University();
        System.out.println("\nRead xml file sucess\n");
        System.out.println(university.getStudents());
        System.out.println(university.getTeachers());
        System.out.println(university.getClassrooms());
        System.out.println(university.getCourses());
    }
}
