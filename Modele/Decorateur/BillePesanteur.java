package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class BillePesanteur extends DecorateurBille {
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
