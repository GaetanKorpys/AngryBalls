package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.Modele.OutilsBille;

import java.util.Vector;

public class BilleNewton extends DecoratorBille {

    public BilleNewton(Bille bille) {
        super(bille);
    }

    @Override
    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        this.getAccélération().ajoute(OutilsBille.gestionAccélérationNewton(this, billes));
    }

}
