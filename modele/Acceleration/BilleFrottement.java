package exodecorateur_angryballs.maladroit.modele.Acceleration;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class BilleFrottement extends DecoratorBille {
    public BilleFrottement(Bille bille) {
        super(bille);
    }

    @Override
    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        this.getAccélération().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse()));
    }
}
