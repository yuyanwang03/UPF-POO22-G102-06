import java.util.*;
import com.google.zxing.common.BitMatrix;
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

    public Image genDelegateQR(QRLib q){
        Image out = new Image("Lab3/genDelegateQR.png", 200, 200);
        String text = "This is a QR for a Delegate Member. You don't have to care about rising sea levels, if you live on a mega yatch.";
        BitMatrix b = QRLib.generateQRCodeImage(text, 200, 200);
        out.setBitMatrix(b);
        out.save();
        return out;
    }

    public Image genRegularQR(QRLib q){
        Image out = new Image("Lab3/genRegularQR.png", 200, 200);
        String text = "This is a QR for a Delegate Member. Climate change doesn't matter, if you stay indoors.";
        BitMatrix b = QRLib.generateQRCodeImage(text, 200, 200);
        out.setBitMatrix(b);
        out.save();
        return out;
    }

    // public boolean signUpDelegate(Delegate d, QRLib q, Image i){

    // }

    // public boolean signUpRegular(Regular r, QRLib q, Image i){

    // }

    public void proposeAction(Action a){
        this.headOf.getOrganization().addAction(a);
    }

    public void signUpAction(Date d){
        this.headOf.getOrganization().getAction(d).addHeadquarter(headOf);
    }
}
