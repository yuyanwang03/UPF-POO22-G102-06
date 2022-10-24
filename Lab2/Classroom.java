import java.util.*;

public class Classroom {
    private int code;
    private LinkedList<Lecture> lectures;

    public Classroom(int c){
        this.code = c;
        this.lectures = new LinkedList<Lecture>();
    }

    public void addLecture(Lecture l){
        this.lectures.add(l);
    }
    
}
