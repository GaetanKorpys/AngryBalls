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
    public void gestionAcc�l�ration(Vector<Bille> billes) {
        _decoredBille.gestionAcc�l�ration(billes);
        this.getAcc�l�ration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse()));
    }
}
