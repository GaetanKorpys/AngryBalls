package exodecorateur_angryballs.maladroit.Modele.COR;

import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.State.State;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

public abstract class MouseEventCOR {

    protected static Vecteur positionSourisPressed;
    private final MouseEventCOR next;
    protected BillePilote bille;


    public MouseEventCOR(MouseEventCOR m, BillePilote bille) {
        this.next = m;
        this.setBille(bille);
    }


    public boolean traiteEvenement(MouseEvent e, State controleur) {
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

    public abstract boolean reconnaitEvent(MouseEvent e, State controleur);

    public void setBille(BillePilote bille) {
        this.bille = bille;
    }
}
