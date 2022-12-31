package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.DecoratorBille;
import exodecorateur_angryballs.maladroit.Modele.OutilsBille;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public class BilleDivision extends DecoratorBille {
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
                //if(billeCourante.getClass().equals(this.getClass())){
                Vecteur position = billeCourante.getPosition();
                position.ajoute(this.getPosition());
                position.multiplie(0.5);

                Color couleur = new Color((this.getCouleur().getRed()+billeCourante.getCouleur().getRed())/2,(this.getCouleur().getGreen()+billeCourante.getCouleur().getGreen())/2,(this.getCouleur().getBlue()+billeCourante.getCouleur().getBlue())/2);
                Double rayon=(billeCourante.getRayon()+cetteBille.getRayon())/4;

                Vecteur vitesse = this.getVitesse().somme(billeCourante.getVitesse()).produit(0.5);
                if(vitesse.norme()==0){
                    vitesse.somme(new Vecteur(1,0));
                }


                b[0] = new BilleParDefaut(position.somme(vitesse), rayon, new Vecteur(), couleur, getVueBillard());
                b[0] = new BilleMRU(b[0], vitesse);

                b[1] = new BilleParDefaut(position.somme(vitesse.rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                b[1] = new BilleMRU(b[1], vitesse.rotationQuartDeTour());

                b[2] = new BilleParDefaut(position.somme(vitesse.rotationQuartDeTour().rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                b[2] = new BilleMRU(b[2], vitesse.rotationQuartDeTour().rotationQuartDeTour());

                b[3] = new BilleParDefaut(position.somme(vitesse.rotationQuartDeTour().rotationQuartDeTour().rotationQuartDeTour()), rayon, new Vecteur(), couleur, getVueBillard());
                b[3] = new BilleMRU(b[3], vitesse.rotationQuartDeTour().rotationQuartDeTour().rotationQuartDeTour());

                for (int j = 0; j < 4; j++) {
                    if(rayon>9)
                        b[j] = new BilleDivision(b[j]);
                    b[j] = new BilleRebond(b[j]);
                    b[j] = new BilleSonCollision(b[j], this.getVueBillard().getHurlements());
                    billes.add(b[j]);
                }

                billes.remove(billeCourante);
                billes.remove(this);

                return true;
            }
        }
        return false;
    }
}
