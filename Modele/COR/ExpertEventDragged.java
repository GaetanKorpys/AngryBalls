package exodecorateur_angryballs.maladroit.Modele.COR;

import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.State.State;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

public class ExpertEventDragged extends MouseEventCOR {

    public ExpertEventDragged(MouseEventCOR m, BillePilote bille) {
        super(m, bille);
    }


    @Override
    public boolean reconnaitEvent(MouseEvent e, State controleur) {
        if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
            MouseEventCOR.positionSourisPressed = new Vecteur(e.getX(), e.getY());
            if (this.getBille().pointIsInsideBille(MouseEventCOR.positionSourisPressed)) {
                this.getBille().setCurrentState(controleur.getNext());
            }

            return true;
        }
        return false;
    }
}

