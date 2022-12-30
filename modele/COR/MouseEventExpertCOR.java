package exodecorateur_angryballs.maladroit.modele.COR;

import exodecorateur_angryballs.maladroit.modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.modele.State.ControllerOK;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

public abstract class MouseEventExpertCOR   {

    protected static Vecteur positionSourisPressed;
    private final MouseEventExpertCOR next;
    protected BillePilote bille;


    public MouseEventExpertCOR(MouseEventExpertCOR m, BillePilote bille) {
        this.next = m;
        this.setBille(bille);
    }


    public boolean traiteEvenement(MouseEvent e, ControllerOK controleur) {
        boolean aReconnuEvent = this.reconnaitEvent(e, controleur);

        if (aReconnuEvent)
            return true;
        if (this.next != null)
            return this.next.traiteEvenement(e, controleur);
        else
            return false;
    }


    public BillePilote getBille() {
        return bille;
    }

    public abstract boolean reconnaitEvent(MouseEvent e, ControllerOK controleur);

    public void setBille(BillePilote bille) {
        this.bille = bille;
    }
}
