import java.util.*;

import com.google.zxing.multi.qrcode.QRCodeMultiReader;

public class Delegate extends Member {
    private LinkedList<Regular> dependents;
    private Headquarter headOf;

    public Delegate(String n, int p, String e, Headquarter h){
        super(n, p, e, h);
        this.dependents = new LinkedList<Regular>();
    }

    public void setHeadOf(Headquarter h){
        this.headOf = h;
    }

    public void addDependents(Regular r){
        this.dependents.add(r);
    }

    // public Image genDelegateQR(QRLib q){

    // }

    // public Image genRegularQR(QRlib q){

    // }

    // public boolean signUpDelegate(Delegate d, QRLib q, Image i){

    // }

    // public boolean signUpRegular(Regular r, QRLib q, Image i){

    // }

    public void proposeAction(Action a){

    }

    public void signUpAction(Date d){

    }
    
}
