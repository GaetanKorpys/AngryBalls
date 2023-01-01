package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class BilleChangementCouleur extends DecorateurBille {
    public BilleChangementCouleur(Bille bille) {
        super(bille);
    }

    @Override
    public boolean collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        boolean res = _decoredBille.collisionContour(abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);

        if(res)
        {
            int r,g,b;
            r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            b = ThreadLocalRandom.current().nextInt(0, 255 + 1);

            _decoredBille.setCouleur(new Color(r,g,b));
        }
        return res;
    }
}
