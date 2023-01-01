package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import exodecorateur_angryballs.maladroit.Modele.OutilsBille;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public class BilleDivision extends DecorateurBille {
    public BilleDivision(Bille bille) {
        super(bille);
    }

    @Override
    public  boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        Bille cetteBille = _decoredBille;
        Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);
        Bille billeCourante;
        Bille[] b = new Bille[4];

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

                    //Idem que billeFusion
                    Color couleur = new Color((this.getCouleur().getRed() + billeCourante.getCouleur().getRed()) / 2, (this.getCouleur().getGreen() + billeCourante.getCouleur().getGreen()) / 2, (this.getCouleur().getBlue() + billeCourante.getCouleur().getBlue()) / 2);
                    Double rayon = (billeCourante.getRayon() + cetteBille.getRayon()) / 4;

                    Vecteur vitesse = this.getVitesse().somme(billeCourante.getVitesse()).produit(0.5);
                    if (vitesse.norme() == 0) {
                        vitesse.somme(new Vecteur(1, 0));
                    }


                    //Crétation des 4 billes
                    b[0] = new BilleParDefaut(pos.somme(vitesse), rayon, new Vecteur(), couleur, getVueBillard());
                    b[0] = new BilleMRU(b[0], vitesse);

                    b[1] = new BilleParDefaut(pos.somme(vitesse.rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                    b[1] = new BilleMRU(b[1], vitesse.rotationQuartDeTour());

                    b[2] = new BilleParDefaut(pos.somme(vitesse.rotationQuartDeTour().rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                    b[2] = new BilleMRU(b[2], vitesse.rotationQuartDeTour().rotationQuartDeTour());

                    b[3] = new BilleParDefaut(pos.somme(vitesse.rotationQuartDeTour().rotationQuartDeTour().rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                    b[3] = new BilleMRU(b[3], vitesse.rotationQuartDeTour().rotationQuartDeTour().rotationQuartDeTour());


                    for (int j = 0; j < 4; j++) {
                        b[j] = new BilleRebond(b[j]);
                        b[j] = new BillePilote(b[j]);
                        if (rayon > 15)
                            b[j] = new BilleDivision(b[j]);
                        b[j] = new BilleSonCollision(b[j], this.getVueBillard().getHurlements());
                        //On ajoute les 4 billes
                        billes.add(b[j]);
                    }

                    int index = 0;
                    for (Bille bille:billes) {
                        if(bille.getClef() == this.getClef())
                            index = billes.indexOf(bille);
                    }

                    //On supprime les 2 billes entrés en collision
                    billes.removeElementAt(index);
                    billes.remove(billeCourante);
                    return true;
                //}
            }
        }
        return false;
    }
}
