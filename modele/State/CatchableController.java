package exodecorateur_angryballs.maladroit.modele.State;


import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.COR.MouseEventPressed;
import exodecorateur_angryballs.maladroit.modele.Decorateur.BillePilote;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class CatchableController extends ControllerOK {

    public CatchableController(BillePilote bille, ControllerOK next, ControllerOK previous) {
        super(bille, next, previous);
        this.expert = new MouseEventPressed(null, bille);
    }

    @Override
    public void dessine(Bille g, Graphics x) {
        //System.out.println("Ne dessine rien");
    }

    @Override
    public void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e) {
        //System.out.println("Pas d'accel");
    }

}
