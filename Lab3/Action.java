import java.util.*;

public class Action {
    private String purpose;
    private Date start;
    private int duration;
    private LinkedList<InfoAction> developedAction;

    public Action(String p, Date s, int d) {
        this.purpose = p;
        this.start = s;
        this.duration = d;
        developedAction = new LinkedList<InfoAction>();
    }

    public void addHeadquarter(Headquarter h) {
        //this.headquarter.add(h);
    }
}
