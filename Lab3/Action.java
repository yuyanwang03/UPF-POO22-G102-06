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
        InfoAction tempInfoAction = new InfoAction(this, h, h.getMembers().size(), 0, false);
        this.developedAction.add(tempInfoAction);
    }

    public void addInfoAction(InfoAction i){
        this.developedAction.add(i);
    }

    public Date getDate(){
        return this.start;
    }
}
