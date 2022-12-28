package exodecorateur_angryballs.maladroit.modele.State;


import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.COR.MouseEventPressed;
import exodecorateur_angryballs.maladroit.modele.Catch.BillePilote;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class CatchableController extends ControllerOK {

    public CatchableController(BillePilote bille, ControllerOK next, ControllerOK previous) {
        super(bille, next, previous);
        this.expert = new MouseEventPressed(null, bille);
    }

    @Override
    public void dessine(Bille g, Graphics x) {}

    @Override
    public void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e) {}

}
