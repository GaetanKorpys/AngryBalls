package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import exodecorateur_angryballs.maladroit.Modele.OutilsBille;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public class BilleFusion extends DecorateurBille {


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
                billeCourante.getPosition(), billeCourante.getRayon(), billeCourante.getVitesse(), billeCourante.masse()))
            {
                //if(billeCourante.getClass().equals(this.getClass())) {
                    Vecteur pos = billeCourante.getPosition();
                    //On récupère le milieu des 2 billes
                    pos.ajoute(this.getPosition());
                    pos.multiplie(0.5);

                    //On modifie la couleur r,g,b de la nouvelle bille
                    Color couleur = new Color((this.getCouleur().getRed() + billeCourante.getCouleur().getRed()) / 2, (this.getCouleur().getGreen() + billeCourante.getCouleur().getGreen()) / 2, (this.getCouleur().getBlue() + billeCourante.getCouleur().getBlue()) / 2);
                    Double rayon = (billeCourante.getRayon() + this.getRayon()/2);


                    Bille billeFusion = new BilleParDefaut(pos, rayon, new Vecteur(), couleur, getVueBillard());
                    billeFusion = new BilleMRU(billeFusion, this.getVitesse().somme(billeCourante.getVitesse()).produit(0.5));
                    billeFusion = new BilleRebond(billeFusion);
                    billeFusion = new BillePilote(billeFusion);
                    billeFusion = new BilleFusion(billeFusion);
                    billeFusion = new BilleSonCollision(billeFusion, this.getVueBillard().getHurlements());


                    int index = 0;
                    for (Bille bille:billes) {
                        if(bille.getClef() == this.getClef())
                            index = billes.indexOf(bille);
                    }

                    //On supprime les 2 billes entrés en collision
                    billes.removeElementAt(index);
                    billes.remove(billeCourante);

                    //On ajoute la nouvelle bille décorée
                    billes.add(billeFusion);

                    return true;
                //}
            }
        }
        return false;
    }
}
