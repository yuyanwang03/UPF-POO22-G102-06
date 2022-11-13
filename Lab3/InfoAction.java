import java.util.*;

public class InfoAction {
    private Action action;
    private Headquarter headquarter;
    private int numMembers;
    private int numNonMembers;
    private boolean pressCoverage;

    public InfoAction(Action a, Headquarter h, int nm, int nn, boolean pc) {
        this.action = a;
        this.headquarter = h;
        this.numMembers = nm;
        this.numNonMembers = nn;
        this.pressCoverage = pc;
    }

    public Action getAction(){
        return this.action;
    }

    public Headquarter getHeadquarter(){
        return this.headquarter;
    }
}
