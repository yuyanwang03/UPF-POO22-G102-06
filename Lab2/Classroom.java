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

}
