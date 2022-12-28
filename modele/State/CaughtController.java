package exodecorateur_angryballs.maladroit.modele.State;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.COR.MouseEventReleased;
import exodecorateur_angryballs.maladroit.modele.Catch.BillePilote;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class CaughtController extends ControllerOK {


    public CaughtController(BillePilote bille, ControllerOK next, ControllerOK previous) {
        super(bille, next, previous);
        this.expert = new MouseEventReleased(this.expert, bille);
    }

    @Override
    public void dessine(Bille g, Graphics x) {
        int width, height;
        int xMin, yMin;
        xMin = (int) (Math.round(g.getPosition().x - g.getRayon()) +g.getRayon()*0.40 );
        yMin = (int) (Math.round(g.getPosition().y - g.getRayon()) +g.getRayon()*0.40);
        width = height =(int)( 2 *  Math.round(g.getRayon()) * 0.60);
        x.setColor(Color.WHITE);//black
        x.fillOval(xMin, yMin, width, height);
    }
    public Double calculate(Double base, Double n) {
        return Math.pow(Math.E, Math.log(base)/n);
    }

    @Override
    public void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e) {


        if(g.pointIsInsideBille(new Vecteur(e.getX(), e.getY()))){

            g.getAccélération().ajoute(MecaniquePoint.freinageFrottement(g.masse()*0.1 , g.getVitesse().produit(g.masse()/10000)));

        }else {
            Vecteur position = g.getPosition();
            Vecteur actuel = new Vecteur(e.getX(), e.getY());
            Vecteur direction = actuel.difference(position);
            direction = direction.produit(0.0001);
            direction = direction.produit(10.0);
            double reducteur = 5;
            if (direction.x < 0) {
                direction.x = -calculate(-direction.x, reducteur);
            } else {
                direction.x = calculate(direction.x, reducteur);

            }
            if (direction.y < 0) {
                direction.y = -calculate(-direction.y, reducteur);
            } else {
                direction.y = calculate(direction.y, reducteur);

            }
            direction = direction.produit(0.01);

            g.getAccélération().ajoute(direction);
            g.getAccélération().ajoute(MecaniquePoint.freinageFrottement(g.masse() * 0.1, g.getVitesse().produit(g.masse() / 10000)));


        }

    }


}

