package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;


public class BilleMRU extends DecorateurBille {

    public BilleMRU(Bille bille, Vecteur vitesse) {
        super(bille);
        _decoredBille.setVitesse(vitesse);
    }
}
