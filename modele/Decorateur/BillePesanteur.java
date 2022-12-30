package exodecorateur_angryballs.maladroit.modele.Decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class BillePesanteur extends DecoratorBille {
    private Vecteur pesanteur;

    public BillePesanteur(Bille bille, Vecteur pesanteur) {
        super(bille);
        this.pesanteur = pesanteur;
    }

    @Override
    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        this.getAccélération().ajoute(this.pesanteur);
    }
}
