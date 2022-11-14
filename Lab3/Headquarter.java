import java.util.*;

public class Headquarter {
    private String name;
    private String email;
    private LinkedList<Member> members;
    private Delegate head;
    private LinkedList<InfoAction> actionsParticipated;
    private Organization organization;
    private LinkedList<City> cities;

    public Headquarter(String n, String e, Organization o){
        this.name = n;
        this.email = e;
        this.organization = o;
        this.members = new LinkedList<Member>();
        this.actionsParticipated = new LinkedList<InfoAction>();
        this.cities = new LinkedList<City>();
    }

    public void addMember(Member m){
        this.members.add(m);
    }

    public void setCities(LinkedList<City> c){
        this.cities.addAll(c);
    }

    public void signUpAction(Action a, int members, int nonMembers, boolean press){
        InfoAction tempInfoAction = new InfoAction(a, this, members, nonMembers, press);
        this.actionsParticipated.add(tempInfoAction);
        a.addInfoAction(tempInfoAction);
    }

    public Organization getOrganization(){
        return this.organization;
    }

    public LinkedList<Member> getMembers(){
        return this.members;
    }

    public Action getAction(Date d){
        for (int i = 0; i<this.actionsParticipated.size(); i++){
            if (actionsParticipated.get(i).getAction().getDate().equals(d)){
                return actionsParticipated.get(i).getAction();
            }
        }
        return null;
    }

    public void setHead(Delegate d){
        this.head = d;
        // Delegate is also a member of the headquarter
        this.members.add(d);
    }

    public Delegate getHead(){
        return this.head;
    }

    public String toString(){
        return this.name;
    }
}
