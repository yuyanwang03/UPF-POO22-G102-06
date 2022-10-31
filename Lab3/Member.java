import java.util.*;

public class Member {
    private String name;
    private int phone;
    private String email;
    private Availability available;
    private Headquarter headquarter;

    public Member(String n, int p, String e, Headquarter h){
        this.name = n;
        this.phone = p;
        this.email = e;
        this.headquarter = h;
    }

    public void setAvailability(Availability a){
        this.available = a;
    }

    public Headquarter getHeadquarter(){
        return this.headquarter;
    }
    
}
