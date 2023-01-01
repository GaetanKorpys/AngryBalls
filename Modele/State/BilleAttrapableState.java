package exodecorateur_angryballs.maladroit.Modele.State;


import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.COR.ExpertEventDragged;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class BilleAttrapableState extends State {

    public BilleAttrapableState(BillePilote bille, State next, State previous) {
        super(bille, next, previous);
        this.expert = new ExpertEventDragged(null, bille);
    }

    //La bille n'est pas attrapée donc on ne fait rien
    @Override
    public void dessine(Bille g, Graphics x) {
        //Todo
    }

    @Override
    public void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e) {
        //Todo
    }

}
