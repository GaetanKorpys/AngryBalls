package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class BilleFrottement extends DecorateurBille {
    public BilleFrottement(Bille bille) {
        super(bille);
    }

    @Override
    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        this.getAccélération().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse()));
    }
}
