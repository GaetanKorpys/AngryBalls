package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.Modele.State.CatchableController;
import exodecorateur_angryballs.maladroit.Modele.State.CaughtController;
import exodecorateur_angryballs.maladroit.Modele.State.ControllerOK;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;


public class BillePilote extends DecoratorBille {

    ControllerOK controleurCourant;
    CatchableController controleurAttrapable;
    CaughtController controleurAttrapee;
    MouseEvent e;

    public BillePilote(Bille bille) {
        super(bille);
        genererGrapheControleurs();
    }

    public void setControleurCourant(ControllerOK controleurCourant) {
        this.controleurCourant = controleurCourant;
    }

    private void genererGrapheControleurs() {
        this.controleurAttrapable = new CatchableController(this, null, null);
        this.controleurAttrapee = new CaughtController(this, this.controleurAttrapable, this.controleurAttrapable);
        this.controleurAttrapable.setPrevious(this.controleurAttrapee);
        this.controleurAttrapable.setNext(this.controleurAttrapee);
        this.controleurCourant = this.controleurAttrapable;
    }

    public void handleMouseEvent(MouseEvent e) {
        this.e =e;
        this.controleurCourant.handleMouseEvent(e);
    }

    /*
    public void dessine(Graphics g) {
        //_decoredBille.dessine(g);
        //controleurCourant.dessine(this,g);
    }*/

    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        controleurCourant.gestionAccélération(this,billes,e);
    }
}
