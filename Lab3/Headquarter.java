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

    public void signUpAction(Action a, int members, int nonMembers, boolean press){

    }

    public Organization getOrganization(){
        return this.organization;
    }

    public Action getAction(Date d){

    }

    public void setHead(Delegate d){
        this.head = d;
    }
}
