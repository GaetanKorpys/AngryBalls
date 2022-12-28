package exodecorateur_angryballs.maladroit.modele.State;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.COR.MouseEventExpertCOR;
import exodecorateur_angryballs.maladroit.modele.Catch.BillePilote;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public abstract class ControllerOK {

    private final BillePilote bille;
    protected MouseEventExpertCOR expert;
    private ControllerOK next, previous;


    public ControllerOK(BillePilote bille, ControllerOK next, ControllerOK previous) {
        this.bille = bille;
        this.setPrevious(previous);
        this.setNext(next);
    }


    public void handleMouseEvent(MouseEvent e) {
        this.expert.traiteEvenement(e, this);
    }


    public ControllerOK getNext() {
        return next;
    }


    public void setNext(ControllerOK next) {
        this.next = next;
    }


    public ControllerOK getPrevious() {
        return previous;
    }


    public void setPrevious(ControllerOK previous) {
        this.previous = previous;
    }


    public BillePilote getBille() {
        return bille;
    }
    public abstract void dessine(Bille g, Graphics x);
    public abstract void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e);

}
