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
        // We have understood that this method should add a headquarter to the organization
        try{
            Organization o = developedAction.get(0).getHeadquarter().getOrganization();
            o.addHeadquarter(h);
        } catch(Exception e){
            System.out.println("Exception thrown when adding a headquarter from an action");
        }
    }

    public void addInfoAction(InfoAction i){
        this.developedAction.add(i);
    }

    public Date getDate(){
        return this.start;
    }
}
