package exodecorateur_angryballs.maladroit.Modele.State;


import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.COR.MouseEventPressed;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;

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
