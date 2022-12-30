package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.Modele.OutilsBille;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public class BilleFusion extends DecoratorBille {


    public BilleFusion(Bille bille) {
        super(bille);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        Bille cetteBille = _decoredBille;
        Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);
        Bille billeCourante;

        int i;
        for (i = 0; i < autresBilles.size(); ++i) {
            billeCourante = autresBilles.get(i);
            if (Collisions.CollisionBilleBille(cetteBille.getPosition(), cetteBille.getRayon(), cetteBille.getVitesse(), cetteBille.masse(),
                    billeCourante.getPosition(), billeCourante.getRayon(), billeCourante.getVitesse(), billeCourante.masse())){
                if(billeCourante.getClass().equals(this.getClass())){

                    Vecteur position = billeCourante.getPosition();
                    position.ajoute(this.getPosition());
                    position.multiplie(0.5);
                    Color couleur = new Color((this.getCouleur().getRed()+billeCourante.getCouleur().getRed())/2,(this.getCouleur().getGreen()+billeCourante.getCouleur().getGreen())/2,(this.getCouleur().getBlue()+billeCourante.getCouleur().getBlue())/2);
                    Double rayon= Math.sqrt(((3.14*billeCourante.getRayon()*billeCourante.getRayon())+(3.14*this.getRayon()*this.getRayon()))/3.14);
                    Bille billeFusion = new BilleParDefaut(position, rayon,this.getVitesse().somme(billeCourante.getVitesse()) ,couleur, getVueBillard());
                    //billeFusion = new BilleMRU(billeFusion, this.getVitesse().somme(billeCourante.getVitesse()).produit(0.5));
                    //billeFusion = new BilleRebond(billeFusion);
                    billeFusion = new BilleFusion(billeFusion);
                    billes.remove(billeCourante);
                    billes.remove(this);
                    billes.add(billeFusion);
                }
                return true;

            }
        }
        return false;
    }
}
