package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class BillePesanteur extends DecoratorBille {
    private Vecteur pesanteur;

    public BillePesanteur(Bille bille, Vecteur pesanteur) {
        super(bille);
        this.pesanteur = pesanteur;
    }

    @Override
    public void gestionAcc�l�ration(Vector<Bille> billes) {
        _decoredBille.gestionAcc�l�ration(billes);
        this.getAcc�l�ration().ajoute(this.pesanteur);
    }
}
