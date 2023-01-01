package exodecorateur_angryballs.maladroit.Modele.State;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.COR.ExpertEventReleased;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class BilleAttrapeState extends State {

    private Double calculate(Double base, Double n) {
        return Math.pow(Math.E, Math.log(base)/n);
    }

    public BilleAttrapeState(BillePilote bille, State next, State previous) {
        super(bille, next, previous);
        this.expert = new ExpertEventReleased(this.expert, bille);
    }

    //Pour dessiner la bille blanche lorsqu'une bille est pilotée
    @Override
    public void dessine(Bille g, Graphics x) {
        int width, height;
        int xMin, yMin;
        xMin = (int) (Math.round(g.getPosition().x - g.getRayon()) +g.getRayon()*0.40 );
        yMin = (int) (Math.round(g.getPosition().y - g.getRayon()) +g.getRayon()*0.40);
        width = height =(int)( 2 *  Math.round(g.getRayon()) * 0.60);
        x.setColor(Color.WHITE);
        x.fillOval(xMin, yMin, width, height);

    }

    //On gère la bille en fonction du mouvement de la souris
    @Override
    public void gestionAccélération(Bille g, Vector<Bille> billes, MouseEvent e) {

        //On vérifie que le curseur de la souris est bien sur la position de la bille
        if(g.pointIsInsideBille(new Vecteur(e.getX(), e.getY()))){
            g.getAccélération().ajoute(MecaniquePoint.freinageFrottement(g.masse()*0.1 , g.getVitesse().produit(g.masse()/10000)));
        }else {
            double reducteur = 5;
            Vecteur pos = g.getPosition();
            Vecteur sourisPos = new Vecteur(e.getX(), e.getY());
            Vecteur direction = sourisPos.difference(pos);
            direction = direction.produit(0.0001);
            direction = direction.produit(10.0);
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

