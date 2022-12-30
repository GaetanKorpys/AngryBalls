package exodecorateur_angryballs.maladroit.modele.Decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;

public class BillePasseMuraille extends DecoratorBille {

    public BillePasseMuraille(Bille bille) {
        super(bille);
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonn�eCoinHautGauche, double largeur, double hauteur) {
        //_decoredBille.collisionContour(abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur);
        Collisions.collisionBilleContourPasseMuraille(this.getPosition(), abscisseCoinHautGauche, ordonn�eCoinHautGauche, largeur, hauteur);
        return false;
    }
}
