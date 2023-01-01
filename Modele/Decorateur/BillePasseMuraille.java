package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

public class BillePasseMuraille extends DecorateurBille {

    public BillePasseMuraille(Bille bille) {
        super(bille);
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        //_decoredBille.collisionContour(abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
        Collisions.collisionBilleContourPasseMuraille(this.getPosition(), abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
        return false;
    }
}
