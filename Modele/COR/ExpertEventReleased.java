package exodecorateur_angryballs.maladroit.Modele.COR;

import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.State.State;


import java.awt.event.MouseEvent;

public class ExpertEventReleased extends MouseEventCOR {

    public ExpertEventReleased(MouseEventCOR m, BillePilote bille) {
        super(m, bille);
    }


    @Override
    public boolean reconnaitEvent(MouseEvent e, State controleur) {
        if (e.getID() == MouseEvent.MOUSE_RELEASED) {
            this.bille.setCurrentState(controleur.getNext());
            return true;
        }
        return false;
    }

}
