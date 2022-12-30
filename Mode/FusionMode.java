package exodecorateur_angryballs.maladroit.Mode;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.BilleParDefaut;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleFusion;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BillePilote;
import exodecorateur_angryballs.maladroit.Modele.Decorateur.BilleRebond;
import exodecorateur_angryballs.maladroit.Vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FusionMode extends Mode{

    public FusionMode(VueBillard vueBillard){super(vueBillard);}

    @Override
    protected void preparerBilles() {
        Bille[] tabBilles = new Bille[11];

        this.rayon = 19;
        int r,g,b;

        /*
        b[0] = new BilleParDefaut(new Vecteur(this.diametre * 2, espaceHauteur + this.rayon), this.rayon, new Color(60, 151, 39));
        b[1] = new BilleParDefaut(b[0].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(71, 0, 204));
        b[2] = new BilleParDefaut(b[1].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(150, 0, 204));
        b[3] = new BilleParDefaut(b[2].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(0, 255, 174));
        b[4] = new BilleParDefaut(b[0].getPosition().somme(new Vecteur(this.diametre, this.rayon)), this.rayon, new Color(153, 128, 0));
        b[5] = new BilleParDefaut(b[4].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(0, 0, 0));
        b[6] = new BilleParDefaut(b[5].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(0, 116, 204));
        b[7] = new BilleParDefaut(b[4].getPosition().somme(new Vecteur(this.diametre, this.rayon)), this.rayon, new Color(204, 0, 0));
        b[8] = new BilleParDefaut(b[7].getPosition().somme(new Vecteur(0, this.diametre + decalageDecollerBilles)), this.rayon, new Color(190, 204, 0));
        b[9] = new BilleParDefaut(b[7].getPosition().somme(new Vecteur(this.diametre, this.rayon)), this.rayon, new Color(255, 0, 111));
        b[10] = new BilleParDefaut(new Vecteur(xMax - 3 * this.diametre, b[9].getPosition().y), this.rayon, new Color(82, 19, 77));
         */
        for (int i = 0; i < 11; i++) {
            r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            b = ThreadLocalRandom.current().nextInt(0, 255 + 1);

            Color color = new Color(r,g,b);
            tabBilles[i] = new BilleParDefaut(Vecteur.créationAléatoire(0, 0, xMax, yMax), rayon, new Vecteur(), color, vueBillard);
            tabBilles[i] = new BilleRebond(tabBilles[i]);
            tabBilles[i] = new BillePilote(tabBilles[i]);
            tabBilles[i] = new BilleFusion(tabBilles[i]);

            this.billes.add(tabBilles[i]);
        }
    }
}
