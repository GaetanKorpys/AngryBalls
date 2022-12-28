package exodecorateur_angryballs.maladroit.modele.CollisionBille;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.modele.OutilsBille;

import java.util.Vector;

public class BilleFantome extends DecoratorBille {
    public BilleFantome(Bille bille) {
        super(bille);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return false;
    }
}
