package exodecorateur_angryballs.maladroit.Modele.COR;

import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.State.ControllerOK;


import java.awt.event.MouseEvent;

public class MouseEventReleased extends MouseEventExpertCOR {

    public MouseEventReleased(MouseEventExpertCOR m, BillePilote bille) {
        super(m, bille);
    }


    @Override
    public boolean reconnaitEvent(MouseEvent e, ControllerOK controleur) {
        if (e.getID() == MouseEvent.MOUSE_RELEASED) {
            this.bille.setControleurCourant(controleur.getNext());
            return true;
        }
        return false;
    }

}
