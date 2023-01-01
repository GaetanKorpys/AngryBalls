package exodecorateur_angryballs.maladroit.Modele.State;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.COR.MouseEventCOR;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public abstract class State {

    private final BillePilote bille;
    protected MouseEventCOR expert;
    private State next, previous;

    //Etat dans le graphe du DP State
    public State(BillePilote bille, State next, State previous) {
        this.bille = bille;
        this.setPrevious(previous);
        this.setNext(next);
    }

    //Envoie l'evenement de la souris au DP Cor
    //Il s'occupe du reste
    public void sendEventToCor(MouseEvent e) {
        this.expert.traiteEvenement(e, this);
    }


    public State getNext() {
        return next;
    }


    public void setNext(State next) {
        this.next = next;
    }


    public State getPrevious() {
        return previous;
    }


    public void setPrevious(State previous) {
        this.previous = previous;
    }


    public BillePilote getBille() {
        return bille;
    }

    public abstract void dessine(Bille g, Graphics x);
    public abstract void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e);

}
