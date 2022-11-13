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
        Image out = new Image("Lab3/genDelegateQR"+headOf+".png", 200, 200);
        String text = this.toString();
        text = text + ". This is a QR for a Delegate Member. You don't have to care about rising sea levels, if you live on a mega yatch.";
        BitMatrix b = QRLib.generateQRCodeImage(text, 200, 200);
        out.setBitMatrix(b);
        out.save();
        return out;
    }

    public Image genRegularQR(QRLib q){
        Image out = new Image("Lab3/genRegularQR"+headOf+".png", 200, 200);
        // We will include the name of the delegate at the begginning of the text (followed by a dot), so it will be easier for further methods to be done
        String text = this.toString();
        text = text + ". This is a QR for a Delegate Member. Climate change doesn't matter, if you stay indoors.";
        BitMatrix b = QRLib.generateQRCodeImage(text, 200, 200);
        out.setBitMatrix(b);
        out.save();
        return out;
    }

    public boolean signUpDelegate(Delegate d, QRLib q, Image i){
        String text = QRLib.decodeQRCodeImage(i.getBitmap());
        String delText = " You don't have to care about rising sea levels, if you live on a mega yatch";
        if (text!=null){
            // Get the delegate name that is in the image text
            String iDelegate = text.split("[.]")[0];
            // If the second part of the text is not the same text required for signing up a delegate member, return false
            if (text.split("[.]")[2].equals(delText) == false) {return false;}
            // Check if the name is essentially the name of the head of the headquarter where the member wants to sign up
            if (d.getHeadquarter().getHead().toString().equals(iDelegate)){
                d.getHeadquarter().setHead(d);
                return true;
            }
            // If not, return false -> the program does not add the member
            return false;
        }
        return false;
    }

    public boolean signUpRegular(Regular r, QRLib q, Image i){
        String text = QRLib.decodeQRCodeImage(i.getBitmap());
        String regText = " Climate change doesn't matter, if you stay indoors";
        if (text!=null){
            // Get the delegate name that is in the image text
            String iDelegate = text.split("[.]")[0];
            // If the second part of the text is not the same text required for signing up a regular member, return false
            if (text.split("[.]")[2].equals(regText) == false) { 
                return false;}
            // Check if the name is essentially the name of the head of the headquarter where the member wants to sign up
            if (r.getResponsible().toString().equals(iDelegate)){
                r.getResponsible().getHeadquarter().addMember(r);
                return true;
            }
            // If not, return false -> the program does not add the member
            return false;
        }
        return false;
    }

    public void proposeAction(Action a){
        this.headOf.getOrganization().addAction(a);
    }

    public void signUpAction(Date d){
        if (this.headOf.getOrganization().getAction(d)!=null){
            this.headOf.getOrganization().getAction(d).addHeadquarter(headOf);
        } else {
            System.out.println("There does not exist any action with date " + d.toString());
        }
    }
}
