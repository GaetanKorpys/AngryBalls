package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class BilleMRU extends DecoratorBille {

    public Vecteur vitesse;

    public BilleMRU(Bille bille, Vecteur vitesse) {
        super(bille);
        this.vitesse=vitesse;
    }

    @Override
    public void gestionAcc�l�ration(Vector<Bille> billes) {
        _decoredBille.gestionAcc�l�ration(billes);
        this.setVitesse(this.vitesse);
    }
}
