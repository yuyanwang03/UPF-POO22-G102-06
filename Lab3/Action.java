import java.util.*;

public class Action {
    private String purpose;
    private Date start;
    private int duration;
    private LinkedList<InfoAction> developedAction;

    public Action(String p, Date s, int d) {
        this.purpose = p;
        //
        this.duration = d;
    }

    public void addHeadquarter(Headquarter h) {
        this.headquarter.add(h);
    }
}
