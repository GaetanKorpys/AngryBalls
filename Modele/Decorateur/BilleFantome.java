package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;

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
