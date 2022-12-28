package exodecorateur_angryballs.maladroit.modele.COR;

import exodecorateur_angryballs.maladroit.modele.Catch.BillePilote;
import exodecorateur_angryballs.maladroit.modele.State.ControllerOK;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

public class MouseEventPressed extends MouseEventExpertCOR {

    public MouseEventPressed(MouseEventExpertCOR m, BillePilote bille) {
        super(m, bille);
    }


    @Override
    public boolean reconnaitEvent(MouseEvent e, ControllerOK controleur) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            MouseEventExpertCOR.positionSourisPressed = new Vecteur(e.getX(), e.getY());
            if (this.getBille().pointIsInsideBille(MouseEventExpertCOR.positionSourisPressed)) {
                this.getBille().setControleurCourant(controleur.getNext());
            }

            return true;
        }
        return false;
    }
}

