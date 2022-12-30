package exodecorateur_angryballs.maladroit.modele.Decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.modele.OutilsBille;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.awt.*;
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
